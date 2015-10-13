<%@ Page Title="" Language="C#" MasterPageFile="~/EHealth.Master" AutoEventWireup="true" CodeBehind="HosDetail.aspx.cs" Inherits="EHealth.HosDetail" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
     <div class="row" style="margin-top: 20px">
        <div class="col-md-3">
            <div>
                <ul class="nav nav-pills nav-stacked">
                    <li class="nav-title"><a href="#">Hospitals</a></li>
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
                        <div class="col-md-6">
                            <asp:Image ID="img_hos" runat="server" Width="100%" style="border-radius:5px"/>
                        </div>
                        <div class="col-md-6" style="text-align:left">
                            <div class="panel">
                            <div class="panel-heading">
                                <asp:Label ID="lbl_name" runat="server" Font-Size="X-Large" Font-Bold="true"></asp:Label>
                            </div>
                            <div class="panel-body">
                            <div><asp:Label ID="lbl_phone" runat="server" Font-Size="Large"></asp:Label></div>
                            <div><asp:Label ID="lbl_add" runat="server" Font-Size="Large"></asp:Label></div>
                            <div><asp:Label ID="lbl_city" runat="server" Font-Size="Large"></asp:Label></div>
                            <div><asp:Label ID="lbl_state" runat="server" Font-Size="Large"></asp:Label></div>
                            </div>
                            </div>
                        </div>
                    </div>
                    <div style="margin-top:20px" class="panel">
                        <div class="panel-heading">
                            <b style="font-size:large">Introduction</b>
                        </div>
                        <div class="panel-body">
                            <asp:Label ID="lbl_introduction" runat="server"></asp:Label>
                        </div>
                    </div>
                    <div style="margin-top:20px" class="panel">
                        <div class="panel-heading">
                            <b style="font-size:large">Location</b>
                        </div>
                        <div class="panel-body">
                            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3173.213842920515!2d-121.93325899999996!3d37.31376200000001!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x0%3A0xfe5d0fcd0be0c3ba!2sSanta+Clara+Valley+Medical+Center!5e0!3m2!1sen!2sus!4v1417827884225" width="100%" height="300" frameborder="2px" style="border:0"></iframe>
                        </div>
                    </div>
               </div>
            </div>
        </div>
     </div>
</asp:Content>
