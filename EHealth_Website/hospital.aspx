<%@ Page Title="" Language="C#" MasterPageFile="~/EHealth.Master" AutoEventWireup="true" CodeBehind="hospital.aspx.cs" Inherits="EHealth.WebForm2" %>
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
                <div style="padding: 20px 30px 70px 30px">
                    <div class="input-group">
                        <asp:Image width="100%" ImageUrl="assets/img/smart.jpg" runat="server" style="border-radius:5px"/>
                    </div>
                    <form runat="server">
                    <div class="input-group">
                        <asp:TextBox ID="txb_hos" placeholder="Input Hospital Name" class="form-control" runat="server"></asp:TextBox>
                        <span class="input-group-btn">
                            <asp:Button ID="btn_search" class="btn btn-default" runat="server" Text="Search" />
                        </span>
                    </div>
                    <div style="margin-top: 20px">
                        <asp:GridView ID="gv_hospital" runat="server" AutoGenerateColumns="False" CellPadding="4"
                        ForeColor="#333333" GridLines="None" Width="100%">
                            <FooterStyle BackColor="#990000" Font-Bold="True" ForeColor="White" />
                            <Columns>
                            <asp:BoundField DataField="f_name" HeaderText="Hospital" ReadOnly="True" />
                            <asp:BoundField DataField="f_hos_type_name" HeaderText="Type" />
                            <asp:BoundField DataField="f_add_street_1" HeaderText="Address" />
                            <asp:BoundField DataField="f_add_city" HeaderText="City" />
                            <asp:BoundField DataField="f_add_state" HeaderText="State" />
                            <asp:BoundField DataField="f_phoneNumber" HeaderText="Phone" />
                            <asp:HyperLinkField HeaderText="Link" Text="Detail" DataNavigateUrlFields="link"/>
                            </Columns>
                        <RowStyle ForeColor="#000066" />
                        <SelectedRowStyle BackColor="#669999" Font-Bold="True" ForeColor="White" />
                        <PagerStyle BackColor="White" ForeColor="#000066" HorizontalAlign="Left" />
                        <HeaderStyle BackColor="#006699" Font-Bold="True" ForeColor="White" />
                        </asp:GridView>
                        <asp:SqlDataSource ID="ds_hospitial" runat="server"></asp:SqlDataSource>
                    </div>
                     </form>
                </div>
            </div>
        </div>
     </div>
    <script type="text/javascript">
        function init() {
            $("#li_home").attr("class", "");
            $("#li_hospital").attr("class", "active");
            $("#li_findDoctor").attr("class", "");
            $("#li_appointment").attr("class", "");
            $("#li_firstaid").attr("class", "");
            $("#li_download").attr("class", "");
            $("#li_msgCenter").attr("class", "");
        }
    </script>
</asp:Content>
