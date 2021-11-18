
function validate() {
    var pwd = $("#user_pwd").val();
    var pwd1 = $("#user_pwd2").val();
    <!-- 对比两次输入的密码 -->
    if(pwd == pwd1)
    {
        $("#tishi").html("两次密码相同");
        $("#tishi").css("color","green");
        $("#submit").removeAttr("disabled");
    }
    else {
        $("#tishi").html("两次密码不相同");
        $("#tishi").css("color","red")
        $("#submit").attr("disabled","disabled");
    }
}
function resubmit(formre) {
    var name= $("#user_name").val();
    var phone= $("#user_phone").val();
    var email= $("#user_email").val();
    var pwd = $("#user_pwd").val();
    var pwd1 = $("#user_pwd2").val();
    var bool=true;
    if(name=="") $("#user_name").focus();
    else if(phone=="") $("#user_phone").focus();
    else if(email=="") $("#user_email").focus();
    else if(pwd=="") $("#user_pwd").focus();
    else if(pwd1=="") $("#user_pwd2").focus();
    else bool=false;
    if(bool){
        alert("内容不能为空，请检查并填写！");
        return false;
    }else return true;
}