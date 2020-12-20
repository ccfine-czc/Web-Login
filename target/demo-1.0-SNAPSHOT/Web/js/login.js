// 轮播图下标
var index = 0;
// 轮播图运行线程
var timer = null;
// 密码登录
var psw_login = true;
var mAccount="";
var mPassword="";
var mPhone="";
var mCheck="";
var wrap = document.querySelector(".wrap");
var banner = document.querySelector("#banner");
var buttons=document.querySelector(".buttons");
var dots = buttons.getElementsByTagName("span");
var account_log=document.querySelector(".account_log");
var phone_log=document.querySelector(".phone_log");
var login_btn=document.querySelector(".login_icon");
var send_btn=document.querySelector(".input_tip");

function next_pic () {
    index=(index+1)%4;
    showCurrentDot();
    var newLeft;
    if(wrap.style.left === "-1500px"){
        newLeft = 0;
    }else{
        newLeft = parseInt(wrap.style.left)-500;
    }
    wrap.style.left = newLeft + "px";
}
function prev_pic () {
    index=(index-1)%4;
    showCurrentDot();
    var newLeft;
    if(wrap.style.left === "0px"){
        newLeft = -1500;
    }else{
        newLeft = parseInt(wrap.style.left)+500;
    }
    wrap.style.left = newLeft + "px";
}
function autoPlay () {
    timer = setInterval(function () {
        next_pic();
    },2000);
}
autoPlay();

function showCurrentDot () {
    for(var i = 0, len = dots.length; i < len; i++){
        dots[i].className = "";
    }
    dots[index].className = "on";
}

showCurrentDot();

for (var i = 0, len = dots.length; i < len; i++){
    (function(i){
        dots[i].onclick = function () {
            var dis = index - i;

            wrap.style.left = (parseInt(wrap.style.left) +  dis * 500)+"px";
            index = i;
            clearInterval(timer);
            timer = setInterval(function () {
                next_pic();
            },2000);
            showCurrentDot();
        }
    })(i);
}

// account_log.onclick=function(){
//     var m_phone=document.querySelector(".m-phone");
//     var m_account=document.querySelector(".m-account");
//     m_phone.style.display="none";
//     m_account.style.display="";
//     psw_login=true;
// };
//
//
// phone_log.onclick=function(){
//     var m_phone=document.querySelector(".m-phone");
//     var m_account=document.querySelector(".m-account");
//     m_phone.style.display="";
//     m_account.style.display="none";
//     psw_login=false;
// };

