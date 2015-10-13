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
        <form id="form1" runat="server" action="FindDoctor.aspx">
        <div class="col-md-9">
            <div style="border-radius: 5px; background-color: #ffeedd">
                <div style="padding: 20px 20px 60px 20px">
                    <div class="panel">
                    <div class="panel-heading">
                        <div>
                            <asp:Image runat="server"  ImageUrl="assets/img/findDoctor.png" Width="100%"> </asp:Image>
                        </div>
                    </div>
                    <div class="panel-body">
                        <div class="input-group has-success has-feedback">
                            <span class="input-group-addon"><strong>Name</strong></span>
                            <asp:TextBox class="form-control" ID="txb_name" runat="server"></asp:TextBox>
                        </div>
                        <div class="input-group has-success has-feedback">
                            <span class="input-group-addon"><strong>Hospital</strong></span>
                            <asp:TextBox class="form-control" ID="txb_hos" runat="server"></asp:TextBox>
                        </div>
                        <div class="input-group has-success has-feedback">
                            <span class="input-group-addon"><strong>Specialty</strong></span>
                            <asp:DropDownList ID="ddl_spec" class="form-control" runat="server">
                            </asp:DropDownList>
                        </div>
                        <div class="input-group has-success has-feedback">
                            <span class="input-group-addon"><strong>Gender</strong></span>
                            <asp:DropDownList ID="ddl_gender" class="form-control" runat="server"></asp:DropDownList>
                        </div>
                        <div class="input-group has-success has-feedback">
                            <span class="input-group-addon"><strong>Available</strong></span>
                            <asp:DropDownList ID="ddl_avl" class="form-control" runat="server"></asp:DropDownList>
                        </div>
                       
                        <div class="input-group" style="float: right;">
                            <asp:Button ID="btn_submit" class="btn btn-primary" runat="server" Text="Search For Doctor" />
                        </div>
                    </div>
                    </div>
                    <div class="panel">
                    <div class="panel_body">
                        <div style="margin:20px 20px 20px 20px">
                        <asp:GridView ID="gv_doctor" runat="server" AutoGenerateColumns="False" CellPadding="4"
                            ForeColor="#333333" GridLines="None" Width="100%">
                            <FooterStyle BackColor="#990000" Font-Bold="True" ForeColor="White" />
                            <Columns>
                                <asp:BoundField DataField="f_name" HeaderText="Name" ReadOnly="True" />
                                <asp:BoundField DataField="f_gender" HeaderText="Gender" />
                                <asp:BoundField DataField="f_spec_name" HeaderText="Specialty" />
                                <asp:BoundField DataField="f_available" HeaderText="IsAvailable" />
                                <asp:HyperLinkField HeaderText="Link" Text="Profile" DataNavigateUrlFields="link" />
                            </Columns>
                            <RowStyle ForeColor="#000066" />
                            <SelectedRowStyle BackColor="#669999" Font-Bold="True" ForeColor="White" />
                            <PagerStyle BackColor="White" ForeColor="#000066" HorizontalAlign="Left" />
                            <HeaderStyle BackColor="#006699" Font-Bold="True" ForeColor="White" />
                        </asp:GridView>
                        <asp:SqlDataSource ID="ds_doctor" runat="server"></asp:SqlDataSource>
                        </div>
                </div>
                </div>
                </div>
            </div>
        </div>
        </form>
    </div>
    <script type="text/javascript">
        function init() {
            $("#li_home").attr("class", "");
            $("#li_hospital").attr("class", "");
            $("#li_findDoctor").attr("class", "active");
            $("#li_appointment").attr("class", "");
            $("#li_firstaid").attr("class", "");
            $("#li_download").attr("class", "");
            $("#li_msgCenter").attr("class", "");
        }
    </script>
</asp:Content>
