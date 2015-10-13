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
    public partial class HosDetail : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            String id = Request.QueryString["hos_id"];
            DBClient dbc = new DBClient();
            DataTable dt = dbc.findHospitalById(id);
            lbl_name.Text = dt.Rows[0]["f_name"].ToString();
            lbl_phone.Text = dt.Rows[0]["f_phoneNumber"].ToString();
            lbl_state.Text = dt.Rows[0]["f_add_state"].ToString();
            lbl_add.Text = dt.Rows[0]["f_add_street_1"].ToString();
            lbl_city.Text = dt.Rows[0]["f_add_city"].ToString();
            lbl_introduction.Text = dt.Rows[0]["f_intro"].ToString();
            img_hos.ImageUrl = "assets/img/" + id + ".jpg";
        }
    }
}