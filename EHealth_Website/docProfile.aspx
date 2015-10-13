<%@ Page Title="" Language="C#" MasterPageFile="~/EHealth.Master" AutoEventWireup="true" CodeBehind="docProfile.aspx.cs" Inherits="EHealth.docProfile" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="row" style="margin-top: 20px">
        <div class="col-md-3">
            <div>
                <ul class="nav nav-pills nav-stacked">
                    <li class="nav-title"><a href="#">Doctors</a></li>
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
                <div style="padding: 20px 30px 20px 30px">
                    <div class="row">
                        <div class="col-md-5">
                            <asp:Image ID="img_doc" runat="server" Width="100%" style="border-radius:3px"/>
                        </div>
                        <form runat="server" action="appointment.aspx" method ="post">
                        <div class="col-md-7" style="text-align:left">
                            <div class="panel">
                            <div class="panel-heading">
                                <asp:Label ID="lbl_name" runat="server" Font-Size="X-Large" Font-Bold="true"></asp:Label>
                            </div>
                            <div class="panel-body">
                            <div><asp:Label ID="lbl_spec" runat="server" Font-Size="Large"></asp:Label></div>
                            <div><asp:Label ID="lbl_avl" runat="server" Font-Size="Large"></asp:Label></div>
                            <div><asp:Label ID="lbl_phone" runat="server" Font-Size="Large"></asp:Label></div>
                            <div><asp:Label ID="lbl_hos" runat="server" Font-Size="Large"></asp:Label></div>
                            <asp:HiddenField ID="hid_docId" runat="server" />
                            <div class="input-group">
                            <asp:Button ID="btn_app" class="btn btn-primary" runat="server" Text="Make Appointment" />
                            </div>
                            </div>
                            </div>
                        </div>
                        </form>
                    </div>
                    <div style="margin-top:20px" class="panel">
                        <div class="panel-heading">
                            <b style="font-size:large">Profile</b>
                        </div>
                        <div class="panel-body">
                            <asp:Label ID="lbl_profile" runat="server"></asp:Label>
                        </div>
                    </div>
               </div>
            </div>
        </div>
     </div>
</asp:Content>
