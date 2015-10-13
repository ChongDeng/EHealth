<%@ Page Title="" Language="C#" MasterPageFile="~/EHealth.Master" AutoEventWireup="true" CodeBehind="home.aspx.cs" Inherits="EHealth.home" %>
<asp:Content ID="Content1" ContentPlaceHolderID="head" runat="server">
    
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" runat="server">
    <div class="row">
        <div class="col-md-12">
            <div class="jumbotron">
                <h1>EHealth Service</h1>
                <h1 class="lead">Efficient, Economical & Reliable</h1>
                <p>
                    <a class="btn btn-lg btn-success" href="#" role="button">Get Registered</a>
                    <a class="btn btn-lg btn-success" href="#" role="button">Download APP <strong>SOS</strong></a>
                </p>
            </div>
        </div>
    </div>
    <div class="row">
       <div class="col-md-4">
            <div class="s1-jumbotron">
               <div class="s-jumbo-inner" style="color:gray">
                   <h4> Health Online</h4>
                   <p>Convenience at your fingertips.
                    Schedule appointments, and access your medical records with
                    My Health Online. </p>
               </div>
            </div>
       </div>
       <div class="col-md-4">
            <div class="s2-jumbotron">
                <div class="s-jumbo-inner" style="color:gray">
                   <h4 style="color:gray"> Find a Doctor for You</h4>
                   <p>Looking for a new doctor? Get the personal 
                    care you deserve with doctors who are part of
                    the Sutter Health network.</p>
               </div>
            </div>
       </div>
       <div class="col-md-4">
            <div class="s3-jumbotron">
               <div class="s-jumbo-inner" style="color:gray">
                   <h4 style="color:gray">Simple Equation</h4>
                   <p>Whether you're the patient or the
                     doctor, the way we both get better
                     is through working together. Plus each other.</p>
               </div>
            </div>
       </div>
    </div>
    <script type="text/javascript">
        function init() {
            $("#li_home").attr("class", "active");
            $("#li_hospital").attr("class", "");
            $("#li_findDoctor").attr("class", "");
            $("#li_appointment").attr("class", "");
            $("#li_firstaid").attr("class", "");
            $("#li_download").attr("class", ""); 
            $("#li_msgCenter").attr("class", "");
        }
    </script>
</asp:Content>
