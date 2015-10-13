using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Data.SqlClient;
using System.Data;
using System.Configuration;
using System.Data.Common;
using System.Xml;

namespace EHealth.DBC
{
    public class DBClient
    {
        private ConnectionStringSettings settings;
        private DbProviderFactory factory;

        public DBClient()
        {
            String dbType = ConfigurationManager.AppSettings.Get("DataBase");
            this.settings = ConfigurationManager.ConnectionStrings[dbType];
            this.factory = DbProviderFactories.GetFactory(settings.ProviderName);
        }

        public DataTable findDoctorByName(String name)
        {
            using (DbConnection conn = getConnection())
            {
                String query = "SELECT * FROM t_doctors WHERE f_name like '%" + name + "%'";
                conn.Open();
                DbDataAdapter dataAdapter = factory.CreateDataAdapter();
                DbCommand command = factory.CreateCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = query;
                command.Connection = conn;
                dataAdapter.SelectCommand = command;
                dataAdapter.MissingSchemaAction = MissingSchemaAction.AddWithKey;
                DataTable dt = new DataTable("Doctor");
                dataAdapter.Fill(dt);
                conn.Close();
                return dt;
            }
        }

        public DataTable findDoctorById(String id)
        {
            using (DbConnection conn = getConnection())
            {
                String query = "SELECT * FROM t_doctors t1 LEFT JOIN t_doc_specialty t2 ON t1.f_specialty = t2.f_spec_id LEFT JOIN t_hospitals t3 on t1.f_hos_id = t3.f_id WHERE t1.f_id = " + id;
                conn.Open();
                DbDataAdapter dataAdapter = factory.CreateDataAdapter();
                DbCommand command = factory.CreateCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = query;
                command.Connection = conn;
                dataAdapter.SelectCommand = command;
                dataAdapter.MissingSchemaAction = MissingSchemaAction.AddWithKey;
                DataTable dt = new DataTable("Doctor");
                dataAdapter.Fill(dt);
                conn.Close();
                return dt;
            }
        }

        public DataTable findDoctor(String name, String gender, String specialty, String hos_id, String isAvailable)
        {
            using (DbConnection conn = getConnection())
            {
                String query = "SELECT * FROM t_doctors LEFT JOIN t_doc_specialty ON f_specialty = f_spec_id WHERE";
                if (name != null && !name.Equals(""))
                {
                    query += " f_name like '%" + name + "%' AND";
                }
                if (gender != null && !gender.Equals(""))
                {
                    query += " f_gender = '" + gender + "' AND";
                }
                if (specialty != null && !specialty.Equals(""))
                {
                    query += " f_specialty = " + specialty + " AND";
                }
                if (hos_id != null && !hos_id.Equals(""))
                {
                    query += " f_hos_id = " + hos_id + " AND";
                }
                if (isAvailable.Equals("true"))
                {
                    query += " f_available = 1";
                }
                else
                {
                    query = query.Substring(0, query.Length - 3);
                }

                conn.Open();
                DbDataAdapter dataAdapter = factory.CreateDataAdapter();
                DbCommand command = factory.CreateCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = query;
                command.Connection = conn;
                dataAdapter.SelectCommand = command;
                dataAdapter.MissingSchemaAction = MissingSchemaAction.AddWithKey;
                DataTable dt = new DataTable("Doctor");
                dataAdapter.Fill(dt);
                conn.Close();
                return dt;
            }
        }

        public DataTable getHospitalInfo()
        {
            using (DbConnection conn = getConnection())
            {
                String query = "SELECT * FROM t_hospitals t1 LEFT JOIN t_hos_type t2 on t1.f_type = t2.f_hos_type_id";
                conn.Open();
                DbDataAdapter hosAdapter = factory.CreateDataAdapter();
                DbCommand command = factory.CreateCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = query;
                command.Connection = conn;
                hosAdapter.SelectCommand = command;
                hosAdapter.MissingSchemaAction = MissingSchemaAction.AddWithKey;
                DataTable dt = new DataTable("Hospital");
                hosAdapter.Fill(dt);
                conn.Close();
                return dt;
            }
        }

        public DataTable findHospitalByName(String hosName)
        {
            using (DbConnection conn = getConnection())
            {
                String query = "SELECT * FROM t_hospitals t1 LEFT JOIN t_hos_type t2 on t1.f_type = t2.f_hos_type_id WHERE t1.f_name like '%" + hosName + "%'";
                conn.Open();
                DbDataAdapter hosAdapter = factory.CreateDataAdapter();
                DbCommand command = factory.CreateCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = query;
                command.Connection = conn;
                hosAdapter.SelectCommand = command;
                hosAdapter.MissingSchemaAction = MissingSchemaAction.AddWithKey;
                DataTable dt = new DataTable("Hospital");
                hosAdapter.Fill(dt);
                conn.Close();
                return dt;
            }
        }

        public DataTable findHospitalById(String id)
        {
            using (DbConnection conn = getConnection())
            {
                String query = "SELECT * FROM t_hospitals t1 LEFT JOIN t_hos_type t2 on t1.f_type = t2.f_hos_type_id WHERE t1.f_id =" + id;
                conn.Open();
                DbDataAdapter hosAdapter = factory.CreateDataAdapter();
                DbCommand command = factory.CreateCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = query;
                command.Connection = conn;
                hosAdapter.SelectCommand = command;
                hosAdapter.MissingSchemaAction = MissingSchemaAction.AddWithKey;
                DataTable dt = new DataTable("Hospital");
                hosAdapter.Fill(dt);
                conn.Close();
                return dt;
            }
        }

        public DataTable getDoctorType()
        {
            using (DbConnection conn = getConnection())
            {
                String query = "SELECT * FROM t_doc_specialty";
                conn.Open();
                DbDataAdapter hosAdapter = factory.CreateDataAdapter();
                DbCommand command = factory.CreateCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = query;
                command.Connection = conn;
                hosAdapter.SelectCommand = command;
                hosAdapter.MissingSchemaAction = MissingSchemaAction.AddWithKey;
                DataTable dt = new DataTable("Doctor");
                hosAdapter.Fill(dt);
                conn.Close();
                return dt;
            }
        }

        public DataTable getMsg() {
            using (DbConnection conn = getConnection())
            {
                String query = "SELECT * FROM t_msgs";
                conn.Open();
                DbDataAdapter hosAdapter = factory.CreateDataAdapter();
                DbCommand command = factory.CreateCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = query;
                command.Connection = conn;
                hosAdapter.SelectCommand = command;
                hosAdapter.MissingSchemaAction = MissingSchemaAction.AddWithKey;
                DataTable dt = new DataTable("Messages");
                hosAdapter.Fill(dt);
                conn.Close();
                return dt;
            }
        }

        public DataTable getFirstAidInfo()
        {
            using (DbConnection conn = getConnection())
            {
                String query = "SELECT * FROM t_firstAid";
                conn.Open();
                DbDataAdapter hosAdapter = factory.CreateDataAdapter();
                DbCommand command = factory.CreateCommand();
                command.CommandType = CommandType.Text;
                command.CommandText = query;
                command.Connection = conn;
                hosAdapter.SelectCommand = command;
                hosAdapter.MissingSchemaAction = MissingSchemaAction.AddWithKey;
                DataTable dt = new DataTable("FirstAid");
                hosAdapter.Fill(dt);
                conn.Close();
                return dt;
            }
        }

        private DbConnection getConnection()
        {
            DbConnection conn = factory.CreateConnection();
            conn.ConnectionString = settings.ConnectionString;
            return conn;
        }
    }
}