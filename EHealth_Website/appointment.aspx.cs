using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;

namespace EHealth
{
    public partial class appointment : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            String docId = Request.Form["ctl00$ContentPlaceHolder1$hid_docId"];
            if (docId == null)
            {
                img_doc.ImageUrl = "assets/img/default-doctor.png";
                lbl_msg.Text = "You need to find a doctor at first!";
            }
            else
            {
                img_doc.ImageUrl = "assets/img/doc1.png";
                DBC.DBClient dbc = new DBC.DBClient();
                DataTable dt = dbc.findDoctorById(docId);
                lbl_doctor.Text = dt.Rows[0]["f_name"].ToString();
                lbl_hospital.Text = dt.Rows[0]["f_name1"].ToString();
                lbl_specialty.Text = dt.Rows[0]["f_spec_name"].ToString();
            }

        }
    }
}