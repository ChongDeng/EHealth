using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
using EHealth.DBC;
using System.Text;

namespace EHealth
{
    public partial class WebForm3 : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            DBClient dbc = new DBClient();
            DataTable dt = dbc.getFirstAidInfo();
            StringBuilder sb = new StringBuilder();
            foreach (DataRow dr in dt.Rows)
            {
                sb.Append("<div class=\"panel msg\">");
                sb.Append("<div class=\"panel-heading\">");
                sb.Append("<b>" + dr["f_title"] + "</b>");
                sb.Append("</div>");
                sb.Append("<div class=\"panel-body\">");
                sb.Append(dr["f_detail"]);
                sb.Append("</div>");
                sb.Append("</div>");
            }
            lit_firstaid.Text = sb.ToString();
        }
    }
}