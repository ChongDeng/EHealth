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
    public partial class msgCenter : System.Web.UI.Page
    {
        protected void Page_Load(object sender, EventArgs e)
        {
            DBClient dbc = new DBClient();
            DataTable dt = dbc.getMsg();
            int count = 0;
            String rowStart = "<div class=\"row\">";
            String rowEnd = "</div>";
            String colStart = "<div class=\"col-md-4\">";
            String colEnd = "</div>";
            StringBuilder sb = new StringBuilder();
            foreach (DataRow dr in dt.Rows)
            {
                if (count++ % 3 == 0)
                {
                    sb.Append(rowStart);
                }
                sb.Append(colStart);
                sb.Append("<div class=\"panel msg\">");
                sb.Append("<div class=\"panel-body\">");
                sb.Append("<div>");
                sb.Append("From: " + dr["f_location"]);
                sb.Append("</div>");
                sb.Append("<div>");
                sb.Append("Snd At: " + dr["f_snd_time"]);
                sb.Append("</div>");
                sb.Append("<div>");
                sb.Append("Rvd At: " + dr["f_rec_time"]);
                sb.Append("</div>");
                sb.Append("<p>");
                sb.Append(dr["f_content"]);
                sb.Append("</p>");
                sb.Append("</div>");
                sb.Append("</div>");
                sb.Append(colEnd);
                if (count % 3 == 0)
                {
                    sb.Append(rowEnd);
                }
                lit_msgs.Text = sb.ToString();
            }
        }
    }
}