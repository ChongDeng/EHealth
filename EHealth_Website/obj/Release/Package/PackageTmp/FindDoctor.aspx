<%@ Page Title="" Language="C#" MasterPageFile="~/EHealth.Master" AutoEventWireup="true" CodeBehind="FindDoctor.aspx.cs" Inherits="EHealth.WebForm1" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="row" style="margin-top: 20px">
        <div class="col-md-3">
            <div>
                <ul class="nav nav-pills nav-stacked">
                    <li class="nav-title"><a href="#">Find a Doctor</a></li>
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
                <div style="padding: 20px 30px 70px 30px">
                    <div>
                        <b style="font-size:x-large">Find a doctor is right for you...</b>
                    </div>
                    <div>
                    <div class="input-group has-success has-feedback">
                        <span class="input-group-addon"><strong>Name</strong></span>
                        <asp:TextBox class="form-control" ID="TextBox1" runat="server"></asp:TextBox>
                    </div>
                    <div class="input-group has-success has-feedback">
                        <span class="input-group-addon"><strong>Hospital</strong></span>
                        <asp:TextBox class="form-control" ID="TextBox5" runat="server"></asp:TextBox>
                    </div>
                    <div class="input-group has-success has-feedback">
                        <span class="input-group-addon"><strong>Specilaty</strong></span>
                        <asp:DropDownList ID="DropDownList1" class="form-control" runat="server"></asp:DropDownList>
                    </div>
                    <div class="input-group has-success has-feedback">
                        <span class="input-group-addon"><strong>Gender</strong></span>
                        <asp:DropDownList ID="DropDownList2" class="form-control" runat="server"></asp:DropDownList>
                    </div>
                    <div class="input-group has-success has-feedback">
                        <span class="input-group-addon"><strong>Available</strong></span>
                        <asp:DropDownList ID="DropDownList3" class="form-control" runat="server"></asp:DropDownList>
                    </div>
                    <div class="input-group" style="float:right;">
                        <asp:Button ID="Button1" class="btn btn-primary" runat="server" Text="Search For Doctor" />
                    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</asp:Content>
