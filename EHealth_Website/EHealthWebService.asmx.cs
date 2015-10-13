using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Data.SqlClient;
using System.Data;
using EHealth.DBC;
using System.Xml;

namespace EHealth
{
    /// <summary>
    /// Summary description for EHealthWebService
    /// </summary>
    [WebService(Namespace = "http://104.209.40.90/ehealth/")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    // To allow this Web Service to be called from script, using ASP.NET AJAX, uncomment the following line. 
    // [System.Web.Script.Services.ScriptService]
    public class EHealthWebService : System.Web.Services.WebService
    {
        [WebMethod]
        public DataTable findDoctorByName(String name)
        {
            DBClient dbc = new DBClient();
            return dbc.findDoctorByName(name);
        }

        [WebMethod]
        public DataTable findDoctor(String name, String gender, String specialty, String hos_id, String isAvailable)
        {
            DBClient dbc = new DBClient();
            return dbc.findDoctor(name, gender, specialty, hos_id, isAvailable);
        }

        [WebMethod]
        public DataTable getHospitalInfo()
        {
            DBClient dbc = new DBClient();
            return dbc.getHospitalInfo();
        }

        [WebMethod]
        public String getHospitalInfos()
        {
            System.IO.TextWriter tw = new System.IO.StringWriter();
            DBClient dbc = new DBClient();
            dbc.getHospitalInfo().WriteXml(tw);
            return tw.ToString();
        }
    }
}
