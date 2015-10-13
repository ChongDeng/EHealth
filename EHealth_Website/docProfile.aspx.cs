using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

namespace EHealth
{
    public partial class docProfile : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            string docId = Request.QueryString["docId"].ToString();
            img_doc.ImageUrl = "assets/img/doc1.png";
            DBC.DBClient dbc = new DBC.DBClient();
            DataTable dt = dbc.findDoctorById(docId);
            lbl_name.Text = dt.Rows[0]["f_name"].ToString();
            lbl_phone.Text = "Phone: " + dt.Rows[0]["f_phoneNumber"].ToString();
            lbl_spec.Text =  "Specialty: " + dt.Rows[0]["f_spec_name"].ToString();
            lbl_hos.Text = "Hospital: " + dt.Rows[0]["f_name1"].ToString();
            if (dt.Rows[0]["f_available"].ToString().Equals("True")) {
                lbl_avl.Text = "Appointment: Available";
            }
            else
            {
                lbl_avl.Text = "Appointment: Full";
            }
            lbl_profile.Text = lbl_name.Text + " have thirty years experience on " + dt.Rows[0]["f_spec_name"].ToString();
            hid_docId.Value = docId;
        }
    }
}