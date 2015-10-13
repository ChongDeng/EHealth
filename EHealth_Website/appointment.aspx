<%@ Page Title="" Language="C#" MasterPageFile="~/EHealth.Master" AutoEventWireup="true" CodeBehind="appointment.aspx.cs" Inherits="EHealth.appointment" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="row" style="margin-top: 20px">
        <div class="col-md-3">
            <div>
                <ul class="nav nav-pills nav-stacked">
                    <li class="nav-title"><a href="#">Make Appointment</a></li>
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
                        <form runat="server">
                            <div class="col-md-12">
                                <div class="panel">
                                    <div class="panel-heading" style="font-size: x-large">
                                        <asp:Image runat="server" width="100%" ImageUrl="assets/img/appointment.png"/>
                                    </div>
                                    <div class="panel-body">
                                        <div class="row">
                                            <div class="col-md-7">
                                                <div class="input-group has-success" style="margin-top:0">
                                                    <span class="input-group-addon"><strong>Doctor</strong></span>
                                                    <asp:Label ID="lbl_doctor" class="form-control" runat="server" Width="100%"></asp:Label>
                                                </div>
                                                <div class="input-group has-success">
                                                    <span class="input-group-addon"><strong>Specialty</strong></span>
                                                    <asp:Label ID="lbl_specialty" class="form-control" runat="server" Width="100%"></asp:Label>
                                                </div>
                                                <div class="input-group has-success">
                                                    <span class="input-group-addon"><strong>Hospital</strong></span>
                                                    <asp:Label ID="lbl_hospital" class="form-control" runat="server" Width="100%"></asp:Label>
                                                </div>
                                                <div class="input-group has-success">
                                                    <span class="input-group-addon"><strong>Date&Time</strong></span>
                                                    <asp:TextBox ID="txb_time" class="form-control" runat="server" Width="100%" TextMode="DateTime"></asp:TextBox>
                                                </div>
                                                <div class="input-group has-success">
                                                    <span class="input-group-addon"><strong>Description</strong></span>
                                                    <asp:TextBox ID="txb_memo" class="form-control" runat="server" Width="100%" TextMode="MultiLine" Rows="5"></asp:TextBox>
                                                </div>
                                            </div>
                                            <div class="col-md-5">
                                                <div><asp:Image ID="img_doc" runat="server" Width="100%" /></div>
                                                <div><asp:Label ID="lbl_msg" runat="server" Text="" style="font-weight:bold"></asp:Label></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="panel-footer">
                                        <asp:Button ID="btn_submit" class="btn btn-primary btn-lg" runat="server" Text="Submit An Appointment" />
                                        <a class="btn btn-lg btn-warning" href="FindDoctor.aspx" role="button">Find a Doctor</a>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function init() {
            $("#li_home").attr("class", "");
            $("#li_hospital").attr("class", "");
            $("#li_findDoctor").attr("class", "");
            $("#li_appointment").attr("class", "active");
            $("#li_firstaid").attr("class", "");
            $("#li_download").attr("class", "");
            $("#li_msgCenter").attr("class", "");
        }
    </script>
</asp:Content>
