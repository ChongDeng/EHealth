using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using EHealth.DBC;
using System.Data;

namespace EHealth
{
    public partial class WebForm1 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            DBClient dbc = new DBClient();
            if (!txb_name.Text.Equals("") || !txb_hos.Text.Equals("") || !ddl_spec.SelectedValue.Equals("") || !ddl_gender.SelectedValue.Equals(""))
            {
                String name = txb_name.Text;
                String hospital = txb_hos.Text;
                String spec = ddl_spec.SelectedValue;
                String gender = ddl_gender.SelectedValue;
                String isAvailable = ddl_avl.SelectedValue;
                DataTable dt_doc = dbc.findDoctor(name, gender, spec, hospital, isAvailable);
                dt_doc.Columns.Add("link");
                foreach (DataRow dr in dt_doc.Rows)
                {
                    dr.SetField("link", "docProfile.aspx?docId=" + dr["f_id"]);
                }
                gv_doctor.DataSource = dt_doc;
                gv_doctor.DataBind();
            }
            DataTable dt_spec = dbc.getDoctorType();
            ddl_spec.DataSource = dt_spec;
            ddl_spec.DataTextField = "f_spec_name";
            ddl_spec.DataValueField = "f_spec_id";
            ddl_spec.DataBind();

            DataTable dt_gender = new DataTable();
            dt_gender.Columns.Add("text");
            dt_gender.Rows.Add("male");
            dt_gender.Rows.Add("female");
            ddl_gender.DataSource = dt_gender;
            ddl_gender.DataTextField = "text";
            ddl_gender.DataValueField = "text";
            ddl_gender.DataBind();

            DataTable dt_avl = new DataTable();
            dt_avl.Columns.Add("text");
            dt_avl.Rows.Add("true");
            dt_avl.Rows.Add("false");
            ddl_avl.DataSource = dt_avl;
            ddl_avl.DataTextField = "text";
            ddl_avl.DataValueField = "text";
            ddl_avl.DataBind();
        }
    }
}