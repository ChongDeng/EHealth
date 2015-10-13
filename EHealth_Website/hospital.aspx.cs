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
    public partial class WebForm2 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            if (txb_hos.Text.Equals(""))
            {

            }
            else
            {
                String hosName = txb_hos.Text;
                DBClient dbc = new DBClient();
                DataTable dt = dbc.findHospitalByName(hosName);
                dt.Columns.Add("link");
                foreach (DataRow dr in dt.Rows)
                {
                    dr.SetField("link", "HosDetail.aspx?hos_id=" + dr["f_id"]);
                }
                gv_hospital.DataSource = dt;
                gv_hospital.DataBind();
            }
        }

        protected void ds_hospitial_Selecting(object sender, SqlDataSourceSelectingEventArgs e)
        {

        }
    }
}