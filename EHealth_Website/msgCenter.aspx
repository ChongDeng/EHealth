<%@ Page Title="" Language="C#" MasterPageFile="~/EHealth.Master" AutoEventWireup="true" CodeBehind="msgCenter.aspx.cs" Inherits="EHealth.msgCenter" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="row" style="margin-top: 20px">
        <div class="col-md-3">
            <div>
                <ul class="nav nav-pills nav-stacked">
                    <li class="nav-title"><a href="#">Message Center</a></li>
                    <li><a href="#">Health Plans We Accept</a></li>
                    <li><a href="#">About the Doctor Directory</a></li>
                    <li><a href="#">Languages Spoken</a></li>
                    <li><a href="#">Dictionary of Medical Titles</a></li>
                    <li><a href="#">Quality Care for All Patients</a></li>
                    <li><a href="#">How to Choose a PAMF Doctor</a></li>
                    <li><a href="#">My Health Online Login</a></li>
                </ul>
            </div>
        </div>
        <div class="col-md-9">
            <div style="border-radius: 5px; background-color: #ffeedd">
                <div style="padding: 20px 20px 20px 20px">
                    <div style="margin-bottom:20px">
                        <asp:Image runat="server" width="100%" ImageUrl="assets/img/sos.png" style="border-radius:5px"/>
                    </div>
                    <asp:Literal ID="lit_msgs" runat="server"></asp:Literal>
               </div>
            </div>
        </div>
     </div>
     <script type="text/javascript">
        function init() {
            $("#li_home").attr("class", "");
            $("#li_hospital").attr("class", "");
            $("#li_findDoctor").attr("class", "");
            $("#li_appointment").attr("class", "");
            $("#li_firstaid").attr("class", "");
            $("#li_download").attr("class", "");
            $("#li_msgCenter").attr("class", "active");
        }
    </script>
</asp:Content>
