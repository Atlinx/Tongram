<%@ Language=VBScript %>
<html>
<head>
<meta NAME="GENERATOR" Content="Microsoft Visual Studio 6.0">
<meta NAME="DESCRIPTION" CONTENT="The Century Old Chinese Tangram Game YI ZHI TU in Latest Java Applet Tongram.">
<meta NAME="MS.LOCALE" CONTENT="EN-US">
<title>The Tongram Game</title>
<style TYPE="text/css">
<!--
.8V {font-size: 8pt; font-family: verdana, arial, helvetica, sans-serif;}
.10V {font-size: 10pt; font-family: verdana, arial, helvetica, sans-serif;}
.nounder {text-decoration: none; font-family: verdana, arial, helvetica, sans-serif;}
.14V {font-size: 14pt; color: #FF3300; font-family: verdana, arial, helvetica, sans-serif;}
.18G {font-size: 18pt; font-family: georgia, times new roman, times roman, serif;}
.40V {font-size: 40pt; font-family: verdana, arial, helvetica, sans-serif;}
.COPY {font-size: 8pt; font-family: verdana, arial, helvetica, sans-serif;}
.RedConf8V {font-size: 8pt; color: red; font-family: verdana, arial, helvetica, sans-serif;}
.RedConf10V {font-size: 10pt; color: red; font-family: verdana, arial, helvetica, sans-serif;}
a:link: {color:#003399;}
a:hover:	{color:red;}
-->
</style>
<%
	use DBI;


	$dbh = DBI->connect('dbi:Oracle:', 'ds', 'oracle');
        $sqltxt = 'insert into user_request values ( req_seq_num.nextval,'
	 . '\'' . $Request->ServerVariables('REMOTE_ADDR') . '\','
	 . '\'http://' . $Request->ServerVariables('SERVER_NAME') 
#	. ':' . $Request->ServerVariables('SERVER_PORT') 
	. $Request->ServerVariables('SCRIPT_NAME') . '\',' . '\'' 
	. $Request->ServerVariables('QUERY_STRING') . '\', sysdate)';
 
        my $sth = $dbh->prepare( $sqltxt );
	$sth->execute();

	$dbh->disconnect();


%>

</head>
<body BGCOLOR="#ffffff" TOPMARGIN="0" LEFTMARGIN="0" MARGINWIDTH="0" MARGINHEIGHT="0" ALINK="#003399" LINK="#003399" VLINK="#003399" TEXT="#000000">



<p><a NAME="TOP"></a></p>
<basefont FACE="Verdana, Arial, Helvetica" CLASS="10V" SIZE="2" COLOR="#000000"><!--TOOLBAR_START-->
<script LANGUAGE="javascript">
<!--
function turnRed() {
	what = window.event.srcElement;
	if (what.tagName == "IMG") {
		what.src = what.src.substring(0,(what.src.indexOf(".gif"))) + "-red.gif";
		window.event.cancelBubble = true;
	}
}

function turnWhite () {
	what = window.event.srcElement
	if (what.tagName == "IMG") {
		what.src = what.src.substring(0,(what.src.indexOf("-red.gif"))) + ".gif";
		window.event.cancelBubble = true;
	}
}
var winopen=false
var winTongram = null
function tongram(title, game_id, chips) {
	winTongram=  window.open("","wTongram","width=624,height=496")
	winTongram.document.open()
	winTongram.document.write("<head><title>"+title+"</title></head><body  BGCOLOR='#FFFFFF'><APPLET code='Tongram.class' width=608 height=480><PARAM name=index value='"+ game_id + "'><PARAM name=chips value='"+chips+"'></APPLET><p><font SIZE='-2' COLOR='#000000'>&nbsp; Copyright © <!--#include file="../this_year.txt"--> Tongram.com  All rights reserved.</font></p></body>")
	winTongram.document.close()
	winTongram.focus()
	winopen = true
}

//-->
</script>


<p><span ID="StartMenu" STYLE="DISPLAY: none; POSITION: absolute"></span></p>

<table WIDTH="100%" CELLPADDING="0" CELLSPACING="0" BORDER="0">
  <tr>
    <td WIDTH="459" ROWSPAN="2" VALIGN="top" NOWRAP onmouseover="turnRed()" onmouseout="&#13;&#10;turnWhite()"><nobr><font FACE="Arial, Helvetica" SIZE="1"><A href="../default.asp"><IMG alt="Tongs Gallery Home" border=0 height=21 src="home.gif" width=130></A><A href="../danian/artist.asp" target=_top><IMG alt=Artists border=0 height=21 src="artists.gif" width=66></A><A href="../danian/painting.asp" target=_top><IMG alt=Paintings border=0 height=21 src="paintings.gif" width=79></A><A href="../danian/default.asp" target=_top><IMG alt=Inscription border=0 height=21 src="inscription.gif" width=91></A><A href="../danian/calligraphy.asp" target=_top><IMG alt=Calligraphy border=0 height=21 src="calligraphy.gif" width=97></A><A href="default.asp"><IMG alt=Games border=0 height=21 src="games_on.gif" width=56></A><A href="../../gb/games/default.asp"><IMG alt="Chinese Simplified (GB2312)" border=0 height=21 src="../images/chinese_gb.gif" width=91></A><A href="../../big5/games/default.asp"><IMG alt="Chinese Traditional (Big5)" border=0 height=21 src="../images/chinese_big5.gif" width=91></A></font></nobr></td>
    <td BGCOLOR="#000000" WIDTH="100%" HEIGHT="20"><nobr><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></nobr></td>
    <td WIDTH="90" ROWSPAN="2" ALIGN="right" VALIGN="top" onmouseover="turnRed()" onmouseout="&#13;&#10;turnWhite()"><font FACE="Arial, Helvetica" SIZE="1"><nobr><A href="../8211/index.html" target=_top><IMG alt="Other Links" border=0 height=21 src="other_links.gif" width=90></A></nobr></font></td>
  </tr>
  <tr>
    <td BGCOLOR="#ffffff" WIDTH="100%" HEIGHT="1"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
  </tr>
</table>
<!--TOOLBAR_END-->
<!-- end 1Toolbar_default.inc-->
<!-- start 2Banner_default.inc-->

<table CELLPADDING="0" CELLSPACING="0" WIDTH="997" BORDER="0" height="1015">
  <tr>
<!-- spacer row - specify all others with colspan or rowspan-->
<!--1-->
    <td WIDTH="11" height="3"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=2 ></td>
<!--2-->
    <td WIDTH="137" height="3"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=132 ></td>
<!--3-->
    <td WIDTH="10" height="3"><IMG alt ="" border=0 height=1 src="1ptrans.gif%22" width=2 ></td>
<!--4black_rule-->
    <td WIDTH="10" height="3"><IMG alt ="" border=0 height=1 src="1ptrans.gif%22" width=1 ></td>
<!--5-->
    <td WIDTH="11" height="3"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=9 ></td>
<!--6-->
    <td WIDTH="84" height="3"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=82 ></td>
<!--7-->
    <td WIDTH="11" height="3"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=9 ></td>
<!--8-->
    <td WIDTH="11" height="3"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=9 ></td>
<!--9-->
    <td WIDTH="481" height="3"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=207 ></td>
<!--10-->
    <td WIDTH="11" height="3"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=9 ></td>
<!--11-->
    <td WIDTH="10" height="3"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=2 ></td>
<!--12-->
    <td WIDTH="198" height="3"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=100 ></td>
<!--13-->
    <td WIDTH="12" height="3"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=10 ></td>
  </tr>
  <tr>
<!--NEW EXTERNAL FADE-TO-BLUE HEADS / RED BANNER ##start######### -->
    <td COLSPAN="3" BGCOLOR="#6699cc" width="158" height="70"><p align="center"><img ALIGN="center" VALIGN="TOP" SRC="game_pieces.gif" BORDER="0" HSPACE="0" ALT ="" width="60" height="60" id="IMG1"></p></td>
    <td BGCOLOR="#6699cc" width="10" height="70"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
    <td width="11" height="70"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
    <td COLSPAN="8" width="818" height="70"><img ALIGN="left" VALIGN="TOP" SRC="yizhitu.gif" ALT="Tongram" BORDER="0" width="264" height="68"></td>
  </tr>
<!--blue top line-->
  <tr>
    <td COLSPAN="4" BGCOLOR="#003399" width="168" height="1"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
    <td COLSPAN="9" width="829" height="1"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
  </tr>
<!-- go to...-->
  <tr>
    <td VALIGN="center" ALIGN="middle" COLSPAN="3" BGCOLOR="#ffff99" width="158" height="22"><font FACE="Arial, Helvetica, sans serif" SIZE="-1"><b>contents</b></font></td>
    <td BGCOLOR="#ffff99" width="10" height="22"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
    <td width="11" height="22"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
    <td VALIGN="bottom" ALIGN="left" COLSPAN="8" width="818" height="22"><form NAME="myForm" ACTION ="" target="_top">
<!--START-GO-GO-GO-->
      <select NAME="target" ONCHANGE="PlayGame(this);" LANGUAGE="JavaScript" size="1">
        <option value ="" 
        selected>Click here to select a game to play.</option>
        <option value="javascript:tongram('Play your own puzzle',-1,'-500,-500,0,-500,-500,0,-500,-500,0,-500,-500,0,-500,-500,0,-500,-500,0,-500,-500,0,-500,-500,0,-500,-500,0,-500,-500,0,-500,-500,0,-500,-500,0,-500,-500,0,-500,-500,0,-500,-500,0,-500,-500,0,-500,-500,0,-500,-500,0,-500,-500,0,-500,-500,0')">[Play your own puzzle]</option>
        <option value="javascript:tongram('Tian:the sky;the heavens. day.(Demo Only)',0,'274,250,28,0,314,254,29,0,302,158,28,0,380,298,29,0,315,144,28,0,343,172,28,0,273,186,24,0,313,186,8,0,293,191,24,0,326,161,4,0,413,320,13,0,273,246,8,0,292,246,8,0,273,206,0,1,274,306,8,1')">Tian:the sky;the heavens. day.(Demo Only)</option>
        <option value="javascript:tongram('Zhen:loyal;faithful. chastity or virginity',1,'234,154,0,0,334,347,11,0,244,279,0,0,353,116,28,0,294,219,21,0,310,101,28,0,284,75,20,0,293,194,5,0,309,274,8,0,295,93,4,0,244,279,0,0,324,199,8,1,324,199,0,1,312,131,4,1,265,201,20,0')">Zhen:loyal;faithful. chastity or virginity</option>
        <option value="javascript:tongram('Mu:admire;yearn for',2,'430,299,23,0,335,174,7,0,321,238,28,0,318,238,20,0,280,310,28,0,340,220,28,0,289,171,4,0,317,328,1,0,382,170,8,0,372,323,3,0,317,170,7,0,324,178,0,0,280,310,20,1,308,337,4,0,319,238,28,0')">Mu:admire;yearn for</option>
        <option value="javascript:tongram('Yuan:primary;principal. unit. (Demo Only)',0,'401,268,28,0,249,198,0,0,401,268,4,0,339,137,28,0,283,244,28,0,311,272,28,0,255,272,28,0,283,300,28,0,268,272,0,0,298,272,0,0,284,300,12,0,339,187,28,0,325,201,28,0,339,136,28,1,331,253,20,1')">Yuan:primary;principal. unit. (Demo Only)</option>
        <option value="javascript:tongram('Di:the earth. land;soil. ground. (Demo Only)',0,'386,227,28,0,290,243,0,0,386,227,4,0,248,256,16,0,320,203,0,0,340,144,0,0,290,183,0,0,340,183,0,0,355,238,8,0,335,164,0,0,224,223,8,0,270,183,0,0,310,183,24,1,205,242,4,0,224,223,4,0')">Di:the earth. land;soil. ground. (Demo Only)</option>
        <option value="javascript:tongram('Nan:man;male.',5,'263,136,0,1,251,273,4,0,251,273,0,0,345,252,0,0,343,176,24,0,283,136,0,0,283,196,16,0,283,168,8,0,318,177,16,0,338,196,0,0,236,232,28,0,323,216,16,0,283,136,24,1,349,249,20,0,344,292,28,1')">Nan:man;male.&nbsp;</option>
        <option value="javascript:tongram('Jie:clean',6,'259,188,31,0,288,263,3,0,276,289,3,1,340,291,30,0,305,202,0,0,283,325,9,0,325,162,0,0,325,182,0,0,305,177,8,0,320,237,24,0,407,340,6,0,365,162,0,1,377,242,28,0,334,341,4,0,354,219,28,0')">Jie:clean</option>
        <option value="javascript:tongram('Yu:space;universe;world. (Demo Only)',0,'241,159,0,1,298,182,20,0,325,210,12,1,281,265,12,0,268,345,0,1,288,285,0,0,248,285,24,0,248,285,16,1,293,380,8,1,268,290,8,0,253,237,28,0,288,265,24,1,308,305,0,1,354,181,28,1,262,134,28,0')">Yu:space;universe;world. (Demo Only)</option>
        <option value="javascript:tongram('Huang:yellow (Demo Only)',0,'302,183,8,0,243,162,0,0,287,223,4,1,339,285,7,0,307,230,0,1,324,197,0,0,265,148,4,0,307,258,8,0,369,148,8,0,342,237,0,0,298,157,7,0,324,197,0,0,307,218,0,0,364,120,20,0,241,317,12,1')">Huang:yellow (Demo Only)</option>
        <option value="javascript:tongram('Cai:talent;ability. just. only. not until',9,'222,172,4,0,335,172,20,0,294,272,0,0,354,212,4,0,334,272,0,0,334,312,16,0,374,192,0,0,354,132,0,0,339,292,0,0,349,113,0,0,294,213,0,0,334,232,8,0,334,132,0,0,354,312,0,1,294,272,0,0')">Cai:talent;ability. just. only. not until</option>
        <option value="javascript:tongram('Xiao:imitate;follow the example of',10,'290,304,8,0,193,214,1,0,370,304,0,1,251,247,30,0,311,284,12,0,269,180,29,0,269,330,29,0,235,309,29,0,209,355,5,0,251,174,5,0,304,222,13,0,263,231,29,0,214,341,19,1,346,280,12,0,304,237,3,0')">Xiao:imitate;follow the example of</option>
        <option value="javascript:tongram('Hong:flood. big;vast',11,'233,140,3,0,254,250,11,0,344,213,28,0,372,258,7,0,363,193,0,0,322,194,24,0,362,133,0,0,322,193,16,0,302,189,8,0,327,118,8,0,281,278,30,0,402,193,16,0,322,133,0,0,231,282,4,0,344,213,28,0')">Hong:flood. big;vast</option>
        <option value="javascript:tongram('Zhou:time(conceived as past, present and future)',0,'284,243,0,1,256,175,24,0,313,187,28,0,369,187,28,0,324,294,0,0,304,264,0,0,304,344,8,0,304,294,8,0,284,339,8,0,359,329,0,0,314,186,6,0,344,344,16,0,304,224,0,0,387,271,20,0,327,229,12,1')">Zhou:time(conceived as past, present and future)</option>
        <option value="javascript:tongram('Zhi:know. knowledge. inform',13,'218,125,0,0,171,213,0,0,336,229,4,1,323,300,12,0,237,227,28,0,264,199,4,0,238,284,12,0,291,227,4,0,275,217,20,0,221,294,28,0,336,229,28,0,251,213,28,0,393,229,28,0,278,145,28,1,275,246,20,1')">Zhi:know. knowledge. inform</option>
        <option value="javascript:tongram('Liang:good;fine',14,'260,155,0,0,340,261,29,0,260,235,4,0,288,320,16,0,356,236,29,0,320,194,28,0,298,130,20,1,372,247,29,0,333,249,8,0,360,232,21,0,347,291,9,0,348,194,8,1,288,234,8,0,246,306,4,0,269,103,28,0')">Liang:good;fine</option>
        <option value="javascript:tongram('Ri:sun. daytime. day',15,'310,182,8,0,310,282,0,0,311,225,8,1,310,222,16,0,350,182,0,0,390,182,0,0,350,222,0,0,390,222,0,0,355,202,0,0,385,202,0,0,315,261,3,0,350,222,0,0,310,282,8,0,313,183,20,0,311,225,20,0')">Ri:sun. daytime. day</option>
        <option value="javascript:tongram('Huang:wasteland. desolate;barren. famine',16,'254,136,1,0,232,367,5,0,302,228,28,0,303,303,4,0,265,189,17,0,322,179,6,0,322,179,6,0,313,224,30,0,246,188,9,0,297,213,22,0,340,345,4,0,273,256,24,1,345,123,30,0,344,270,12,0,312,372,12,1')">Huang:wasteland. desolate;barren. famine</option>
        <option value="javascript:tongram('Bi:certainly. must',17,'323,226,1,0,248,146,24,0,300,313,12,1,288,245,0,1,272,229,4,0,299,202,4,0,299,202,28,0,166,235,20,0,180,327,4,0,289,219,12,0,288,186,16,0,258,299,4,1,272,229,28,0,247,254,4,1,138,207,20,1')">Bi:certainly. must</option>
        <option value="javascript:tongram('Guo:cross;pass. mistake',18,'241,227,2,0,354,326,31,0,355,326,27,0,359,236,20,0,379,206,22,0,380,217,0,1,359,293,4,0,380,260,0,0,391,273,21,0,360,255,24,0,364,300,28,0,360,157,0,0,420,300,16,0,301,272,20,1,323,350,11,0')">Guo:cross;pass. mistake</option>
        <option value="javascript:tongram('Ying:be full of. have a surplus',19,'277,262,24,0,203,183,4,0,277,262,28,1,251,204,4,1,279,159,28,0,271,204,0,0,274,165,4,0,266,248,28,0,298,244,14,0,316,259,24,0,292,132,8,0,307,132,28,0,271,204,24,1,334,105,20,0,305,290,12,1')">Ying:be full of. have a surplus</option>
        <option value="javascript:tongram('Yue:moon. month',20,'239,138,31,0,262,272,0,0,262,225,9,1,265,168,6,0,302,232,0,0,342,232,0,0,302,272,0,0,342,272,0,0,307,252,0,0,337,252,0,0,285,265,6,0,302,152,0,0,302,172,0,0,212,258,3,0,343,152,12,0')">Yue:moon. month</option>
        <option value="javascript:tongram('De:get;obtain. satisfied',21,'221,135,0,0,300,191,10,0,249,242,4,1,310,169,12,0,297,233,0,1,317,292,0,0,357,234,24,0,329,272,4,0,342,327,8,0,377,239,8,0,289,259,16,0,317,312,16,1,297,253,8,0,343,171,28,1,310,114,28,0')">De:get;obtain. satisfied</option>
        <option value="javascript:tongram('Gai:change;transform. correct',22,'319,139,0,0,233,287,8,0,399,280,0,1,343,280,12,0,371,252,12,0,268,216,4,0,329,238,4,0,315,280,12,0,300,233,12,0,361,236,20,0,239,159,28,0,282,230,12,0,329,294,12,0,376,162,28,1,303,273,28,1')">Gai:change;transform. correct</option>
        <option value="javascript:tongram('Chen:celestial bodies. time;day',23,'305,123,28,0,350,223,30,0,248,180,0,0,370,142,28,0,328,260,0,0,375,196,4,0,389,211,4,0,348,180,16,0,378,193,4,0,306,295,24,0,361,249,10,0,288,220,8,0,288,220,0,0,297,132,12,1,306,123,12,1')">Chen:celestial bodies. time;day</option>
        <option value="javascript:tongram('Ze:afternoon',24,'443,291,23,0,270,198,0,0,284,264,0,0,374,164,28,0,329,140,28,0,334,104,0,0,338,243,20,0,339,299,4,0,343,134,20,0,321,309,28,0,284,264,0,0,334,104,0,0,338,299,12,0,364,183,28,1,316,183,4,1')">Ze:afternoon</option>
        <option value="javascript:tongram('Mo:no;nothing;none',25,'274,343,8,0,217,181,0,0,220,313,28,1,292,350,7,0,265,166,12,0,262,355,28,0,291,261,12,0,330,155,4,0,300,295,8,0,244,365,28,0,163,313,28,0,275,220,0,0,262,355,12,0,313,165,28,1,270,220,20,0')">Mo:no;nothing;none</option>
        <option value="javascript:tongram('Neng:can;able;capable',26,'287,257,0,1,232,206,4,0,344,179,4,0,381,247,0,0,411,176,20,0,297,240,5,0,415,266,12,0,303,198,29,0,308,309,13,0,317,338,24,0,342,287,28,0,292,263,0,0,314,180,29,0,400,286,28,1,343,237,12,1')">Neng:can;able;capable</option>
        <option value="javascript:tongram('Lie:arrange. list. row.',27,'347,129,0,0,208,154,0,0,347,209,4,0,375,293,20,0,243,255,28,0,218,225,4,0,215,283,28,0,215,283,12,0,232,273,28,0,278,269,28,0,310,254,4,0,271,199,28,0,260,182,28,0,347,265,28,1,260,182,12,1')">Lie:arrange. list. row.</option>
        <option value="javascript:tongram('Su:old;veteran. overnight',28,'261,147,0,1,278,289,4,0,318,172,28,0,345,141,27,0,275,315,0,1,275,275,0,0,347,201,20,0,275,315,0,0,353,283,18,0,270,295,0,0,272,221,28,0,334,249,0,0,374,329,8,1,361,215,12,0,220,275,12,1')">Su:old;veteran. overnight</option>
        <option value="javascript:tongram('Wang:no;not',29,'313,205,8,0,229,282,8,0,353,244,4,0,341,177,28,0,305,265,4,0,305,265,4,0,291,228,4,0,292,271,0,0,322,274,4,0,347,236,0,0,393,205,16,0,272,271,24,1,272,251,8,0,340,314,4,0,261,250,20,0')">Wang:no;not</option>
        <option value="javascript:tongram('Wang:forget. overlook;neglect',30,'279,258,1,1,384,334,11,0,329,234,28,0,346,136,27,0,344,221,28,0,307,214,0,0,320,286,4,0,371,248,4,0,287,219,24,0,354,238,4,0,287,214,8,0,367,174,24,0,347,174,24,0,335,288,28,1,280,259,20,1')">Wang:forget. overlook;neglect</option>
        <option value="javascript:tongram('Han:cold. tremble. needy',31,'269,112,0,0,281,295,31,0,290,265,28,0,229,134,8,0,280,365,12,1,292,330,28,0,256,205,0,0,287,205,0,1,242,250,0,0,322,221,0,0,229,125,20,0,257,225,24,1,247,205,24,1,249,280,28,1,292,135,28,0')">Han:cold. tremble. needy</option>
        <option value="javascript:tongram('Zhang:open;spread;stretch. look',32,'320,247,0,1,323,272,31,0,323,172,4,1,337,230,28,0,320,307,0,0,344,214,20,0,226,271,12,0,344,192,28,0,209,302,4,0,358,270,29,0,306,138,28,0,246,175,28,0,231,303,12,0,260,189,12,0,319,248,12,1')">Zhang:open;spread;stretch. look</option>
        <option value="javascript:tongram('Bi:that;those;the other;another',33,'286,184,31,0,207,214,4,0,270,262,3,0,252,323,20,0,332,216,0,0,332,196,0,0,392,305,20,0,336,277,28,0,315,304,4,0,337,141,8,0,364,277,8,0,322,291,4,0,332,156,0,0,414,210,20,0,198,264,12,1')">Bi:that;those;the other;another</option>
        <option value="javascript:tongram('Tan:talk;chat',34,'261,131,24,0,334,213,29,0,332,213,8,1,292,214,0,0,290,116,4,0,298,158,4,0,392,121,12,0,280,207,28,0,366,205,12,0,283,130,20,0,235,129,5,0,297,159,4,0,239,153,28,0,209,240,4,0,326,75,20,1')">Tan:talk;chat</option>
        <option value="javascript:tongram('Shu:heat;hot weather',35,'239,212,8,0,188,183,0,0,252,212,0,0,275,239,4,1,341,166,20,0,298,112,28,0,279,153,0,0,299,258,4,0,307,224,24,0,309,143,24,0,260,153,28,0,284,68,0,0,322,239,0,1,332,227,12,0,274,142,4,1')">Shu:heat;hot weather</option>
        <option value="javascript:tongram('Lai:come. send here',36,'426,288,21,0,268,188,0,0,287,260,0,0,324,168,26,0,348,128,0,0,308,128,0,0,307,300,16,0,348,128,0,0,332,335,8,0,343,108,0,0,351,172,1,0,308,128,0,0,347,260,0,1,347,260,16,0,315,220,12,1')">Lai:come. send here</option>
        <option value="javascript:tongram('Mi:blown away by wind. not have',37,'252,223,30,0,295,140,0,0,289,209,6,1,426,229,26,0,356,279,12,0,293,289,20,0,313,196,0,0,353,196,0,1,302,247,4,1,349,117,2,0,396,320,6,0,293,196,0,0,373,256,16,0,283,305,4,0,370,264,20,0')">Mi:blown away by wind. not have</option>
        <option value="javascript:tongram('Duan:short;brief. lack;owe',38,'322,328,8,0,274,297,0,0,360,155,28,0,380,198,28,0,276,277,12,0,276,195,4,0,361,277,12,0,321,290,28,0,200,305,4,0,289,188,4,0,230,195,1,0,277,277,4,1,380,198,28,0,263,263,12,0,333,264,4,1')">Duan:short;brief. lack;owe</option>
        <option value="javascript:tongram('Qiu:autumn. harvest time. year',39,'382,195,29,0,369,270,30,0,339,260,1,0,270,269,4,0,316,246,1,0,403,253,4,0,417,267,4,0,256,311,28,0,303,254,1,0,406,250,20,0,297,240,20,0,283,198,28,0,256,254,28,0,288,152,28,1,212,212,28,0')">Qiu:autumn. harvest time. year</option>
        <option value="javascript:tongram('Wang:go. towards. past',40,'260,127,29,0,211,253,5,0,262,278,4,1,324,124,6,0,354,235,0,0,334,254,24,0,338,155,18,0,334,234,0,1,314,249,8,0,374,240,8,0,292,186,28,0,394,274,24,0,334,234,0,0,334,274,0,1,320,214,12,1')">Wang:go. towards. past</option>
        <option value="javascript:tongram('Ji:oneself; one's own; personal',41,'279,267,28,1,256,158,0,0,279,267,4,1,364,295,28,0,336,158,28,0,377,281,4,0,308,185,28,0,405,309,4,0,325,175,28,0,388,298,4,0,392,323,12,0,308,185,28,1,350,171,28,0,377,281,4,0,279,268,4,1')">Ji:oneself; one's own; personal</option>
        <option value="javascript:tongram('Shi:reply on;depend on',42,'408,128,0,0,357,209,0,0,312,228,4,0,368,168,8,0,361,231,20,0,388,319,0,0,428,279,0,0,399,296,5,0,299,239,3,1,413,354,24,0,312,228,4,0,428,239,24,0,428,279,0,1,449,197,28,1,340,255,20,0')">Shi:reply on;depend on</option>
        <option value="javascript:tongram('Dong:winter',43,'297,133,28,0,174,257,0,0,366,285,28,0,298,214,28,0,310,172,28,0,253,286,28,0,323,159,20,0,351,187,4,0,236,296,12,0,334,176,4,0,282,329,4,0,310,257,12,0,239,272,20,1,297,314,28,1,366,285,12,0')">Dong:winter</option>
        <option value="javascript:tongram('Shou:receive;accept. put away. collect',44,'310,152,0,0,394,278,16,0,394,278,0,1,353,278,28,0,340,252,12,0,272,292,0,0,368,253,4,0,292,192,0,0,358,235,4,0,287,172,0,0,224,244,4,0,252,252,0,0,292,192,0,1,210,258,4,0,328,214,12,1')">Shou:receive;accept. put away. collect</option>
        <option value="javascript:tongram('Xin:letter. confidence;trust;faith',45,'268,159,28,0,272,182,0,0,211,215,0,0,231,248,4,0,353,329,0,0,293,309,24,1,349,277,0,1,350,246,0,0,329,272,8,0,330,241,24,0,332,181,7,0,293,309,24,1,313,289,24,1,216,319,4,0,322,212,12,1')">Xin:letter. confidence;trust;faith</option>
        <option value="javascript:tongram('Chang:long. length. be good at',46,'281,274,0,1,310,295,31,0,282,174,4,1,201,233,8,0,296,287,4,0,296,287,4,0,325,206,12,0,328,181,4,0,308,216,12,0,311,192,28,0,281,354,8,1,241,273,8,0,261,273,8,0,321,273,16,1,263,134,28,0')">Chang:long. length. be good at</option>
        <option value="javascript:tongram('Run:leap; intercalation',47,'225,184,0,0,247,242,24,0,351,227,4,0,322,206,28,0,289,296,8,1,289,246,0,0,309,246,24,0,249,189,28,0,269,291,8,0,322,227,28,0,266,201,4,0,294,171,20,1,289,236,0,0,393,185,20,0,379,256,20,0')">Run:leap; intercalation</option>
        <option value="javascript:tongram('Cang:hide;conceal. store',48,'348,215,26,0,239,291,8,0,299,184,30,0,299,126,4,0,311,231,10,0,258,184,20,0,294,302,29,0,387,311,18,0,351,172,20,0,305,267,5,0,210,289,4,0,396,330,18,0,269,274,0,0,335,246,26,1,262,268,20,0')">Cang:hide;conceal. store</option>
        <option value="javascript:tongram('Ke:can;may. but;yet. fit;suit',49,'344,300,28,0,256,226,24,0,316,272,4,0,355,167,8,1,255,187,0,0,295,187,0,0,276,256,8,0,316,235,0,0,275,192,24,0,328,371,24,0,316,272,4,0,256,296,16,1,255,167,24,1,255,167,0,1,315,207,12,1')">Ke:can;may. but;yet. fit;suit</option>
        <option value="javascript:tongram('Shi:use. make. envoy',50,'252,194,29,0,283,283,20,1,339,339,8,0,299,223,4,1,319,183,8,0,319,223,0,0,319,203,8,0,319,263,8,0,354,278,0,1,299,198,24,0,359,279,4,0,359,223,0,1,379,183,24,0,267,311,4,1,260,345,12,1')">Shi:use. make. envoy</option>
        <option value="javascript:tongram('Cheng:accomplish. become. established',51,'211,198,0,0,305,212,9,0,305,212,5,0,289,205,29,0,326,250,29,0,288,277,29,0,304,283,29,0,304,283,13,0,319,270,29,0,273,291,29,0,353,316,3,1,288,277,13,0,342,262,13,0,194,294,4,0,321,294,19,1')">Cheng:accomplish. become. established</option>
        <option value="javascript:tongram('Yu:surplus;spare. more than. after',52,'234,147,28,0,348,174,30,0,307,173,0,0,362,269,6,0,243,257,4,0,332,218,0,0,307,291,11,0,295,274,19,0,367,249,0,1,292,278,3,0,307,173,0,0,352,218,24,0,352,258,0,1,277,191,20,0,216,228,20,1')">Yu:surplus;spare. more than. after</option>
        <option value="javascript:tongram('Qi:implemnet;ware. ',53,'193,264,1,0,317,255,16,0,241,142,4,1,274,131,1,0,296,136,1,0,254,289,20,0,275,132,13,0,327,284,28,0,260,145,13,0,283,145,17,0,288,311,4,0,326,341,12,0,268,275,28,0,292,230,12,0,306,188,3,0')">Qi:implemnet;ware.&nbsp;</option>
        <option value="javascript:tongram('Fu:duplicate. turn around. recover',54,'276,150,28,0,277,263,0,0,264,282,4,1,323,263,20,0,345,330,4,0,302,288,4,0,331,317,4,0,322,325,12,0,362,340,4,0,343,240,2,0,304,198,4,0,327,203,0,0,336,339,12,0,353,148,28,1,210,250,12,1')">Fu:duplicate. turn around. recover</option>
        <option value="javascript:tongram('Lu:law;statue;rule',55,'241,140,29,0,189,264,5,0,247,287,4,1,355,170,4,0,355,207,0,1,295,334,0,0,335,170,0,1,315,190,0,0,295,185,8,0,330,150,0,0,250,209,29,0,355,170,0,1,335,294,0,1,315,251,28,0,293,230,28,0')">Lu:law;statue;rule</option>
        <option value="javascript:tongram('Sui:year. year of age',56,'261,152,0,1,203,283,7,0,312,231,30,0,301,193,8,1,327,319,2,0,293,240,18,0,327,320,30,1,251,307,4,0,338,335,2,0,304,296,2,0,280,190,4,0,241,269,0,0,304,264,2,0,185,295,3,0,279,215,12,1')">Sui:year. year of age</option>
        <option value="javascript:tongram('Nan:difficult;hard',57,'310,200,29,0,328,238,7,0,304,299,4,1,260,193,26,0,348,298,0,0,203,309,4,0,348,287,16,0,328,267,16,0,259,314,29,1,383,281,0,0,251,214,1,0,232,252,28,0,368,298,16,0,348,361,12,0,205,225,28,0')">Nan:difficult;hard</option>
        <option value="javascript:tongram('Yu:desire;wish. want. about to',58,'333,292,27,1,299,232,7,0,219,216,0,0,259,176,0,0,212,178,4,0,212,178,4,0,268,255,4,0,288,307,12,0,294,173,18,0,259,238,20,0,215,270,28,0,316,250,28,0,270,270,28,0,183,293,20,1,327,262,3,0')">Yu:desire;wish. want. about to</option>
        <option value="javascript:tongram('Diao:transfer. accent. tone;tune',59,'259,151,31,0,162,193,0,0,243,230,3,0,237,150,27,0,324,232,0,1,300,244,28,0,304,168,0,0,324,218,0,0,304,213,8,0,309,267,24,0,284,168,28,0,324,138,0,0,364,258,16,0,229,260,4,1,304,298,0,0')">Diao:transfer. accent. tone;tune</option>
        <option value="javascript:tongram('Lu:(music) temperament',60,'269,297,16,0,268,137,20,1,270,297,24,0,325,136,4,0,310,177,28,0,337,203,28,0,355,311,12,0,327,339,28,0,338,322,12,0,321,194,4,0,268,218,12,1,313,326,12,0,327,312,12,0,298,285,12,1,275,209,4,1')">Lu:(music) temperament</option>
        <option value="javascript:tongram('Mo:Chinese ink. pigment. handwriting',61,'274,208,24,0,251,83,0,0,308,307,28,0,308,250,4,0,271,157,0,0,255,124,30,1,268,249,24,0,284,226,28,0,244,134,2,0,326,171,0,0,295,209,28,0,268,289,16,1,311,137,0,1,389,234,18,0,317,153,4,0')">Mo:Chinese ink. pigment. handwriting</option>
        <option value="javascript:tongram('Liang:measure. appraise;evaluate',62,'314,269,24,0,188,161,0,0,268,161,28,1,323,126,28,0,278,249,0,0,283,66,0,0,332,210,12,0,245,223,28,0,313,239,0,1,285,115,24,0,343,297,12,0,283,66,0,0,298,189,0,1,339,146,28,1,266,147,4,1')">Liang:measure. appraise;evaluate</option>
        <option value="javascript:tongram('Yun:cloud. say',63,'230,144,0,1,225,266,0,0,299,228,28,0,276,294,12,0,267,199,0,0,267,159,0,0,316,203,12,0,267,139,0,0,257,194,29,1,247,134,24,0,293,352,4,0,267,159,8,0,327,159,24,0,370,173,20,0,292,334,12,0')">Yun:cloud. say</option>
        <option value="javascript:tongram('Yang:the sun. positive',64,'241,269,0,1,220,290,8,0,337,220,28,0,323,306,0,0,247,223,12,0,332,200,0,1,294,262,20,0,280,304,12,0,236,206,4,0,326,184,3,0,303,215,4,1,312,140,0,0,294,318,12,0,365,304,20,0,204,152,28,0')">Yang:the sun. positive</option>
        <option value="javascript:tongram('Si:silk',65,'357,234,24,0,217,210,5,0,317,194,0,0,338,273,6,0,298,296,20,0,367,252,28,0,367,252,4,0,284,282,20,0,281,285,20,0,357,235,20,0,357,194,12,0,237,275,12,0,328,277,0,1,218,294,4,0,302,193,4,0')">Si:silk</option>
        <option value="javascript:tongram('Bei:sad;sorrowful;melancholy. compassion ',66,'253,252,1,1,269,339,4,0,284,218,28,0,337,267,5,0,340,178,8,0,244,158,0,0,300,158,0,0,264,158,0,1,242,208,8,0,342,210,24,0,268,263,28,0,244,158,0,0,300,218,16,1,335,261,12,0,255,268,20,1')">Bei:sad;sorrowful;melancholy. compassion</option>
        <option value="javascript:tongram('Zhi:send. devote. incur. fine;delicate',67,'326,135,31,0,372,277,16,0,372,277,0,1,334,277,28,0,314,255,12,0,242,258,0,0,242,307,8,0,248,236,28,0,222,302,8,0,277,268,0,0,281,256,6,0,282,307,16,0,248,180,28,0,262,194,12,0,309,214,28,0')">Zhi:send. devote. incur. fine;delicate</option>
        <option value="javascript:tongram('Teng:jump. rise;soar. vacate',68,'305,105,0,0,337,207,31,0,231,224,6,1,326,277,28,0,305,165,0,1,244,190,31,0,311,230,4,1,252,215,6,0,369,142,1,1,292,133,4,0,313,312,0,0,236,165,0,0,339,286,12,1,325,165,28,1,216,265,0,0')">Teng:jump. rise;soar. vacate</option>
        <option value="javascript:tongram('Shi:poetry;verse;poem',69,'346,129,0,0,208,211,0,0,351,209,28,0,234,237,4,0,366,277,8,0,326,337,0,0,346,257,0,0,346,179,0,0,329,294,20,0,326,174,24,0,267,203,7,0,366,297,8,1,366,297,0,1,233,293,4,0,351,209,28,0')">Shi:poetry;verse;poem</option>
        <option value="javascript:tongram('Ran:dye. catch;acquire. contaminate',70,'294,258,8,0,245,228,0,0,350,141,4,1,309,275,29,0,395,182,0,0,322,142,0,0,270,181,3,0,267,206,28,0,275,153,6,0,385,217,24,0,382,320,7,0,350,142,0,0,345,258,0,1,386,271,12,0,279,212,12,1')">Ran:dye. catch;acquire. contaminate</option>
        <option value="javascript:tongram('Lu:dew',71,'252,125,0,1,274,245,24,0,314,149,28,0,287,232,28,0,287,177,0,0,286,109,0,0,269,223,4,0,324,247,12,0,307,182,24,0,309,282,20,0,297,233,0,0,286,149,8,0,337,290,12,0,357,192,12,0,302,324,12,1')">Lu:dew</option>
        <option value="javascript:tongram('Yu:rain',72,'317,165,24,0,344,300,8,0,249,270,4,1,373,271,0,0,284,300,0,0,320,281,5,0,284,220,0,0,264,281,20,0,333,272,21,0,256,294,20,0,316,311,28,0,344,260,8,1,284,300,16,1,284,221,0,1,282,200,12,1')">Yu:rain</option>
        <option value="javascript:tongram('Gao:lamb;kid;fawn',73,'284,308,24,0,215,114,31,0,290,302,28,1,273,289,12,0,298,159,29,0,304,114,5,0,260,275,28,0,288,247,12,0,277,264,28,0,329,267,4,0,289,224,8,0,315,98,29,0,349,184,24,0,307,267,12,0,305,344,12,1')">Gao:lamb;kid;fawn</option>
        <option value="javascript:tongram('Zan:praise. support',74,'279,259,0,1,194,202,0,0,274,202,28,1,299,340,8,0,287,255,12,0,295,182,0,1,244,178,4,0,300,312,4,0,336,241,20,0,258,258,28,0,265,192,5,0,284,269,0,0,295,122,0,0,266,326,28,1,302,230,12,1')">Zan:praise. support</option>
        <option value="javascript:tongram('Wei:do;act. become',75,'329,152,25,0,345,128,5,0,345,128,1,0,321,213,17,0,293,247,17,0,292,246,17,0,332,240,1,0,332,240,1,0,273,246,9,0,311,239,25,0,283,220,1,0,371,210,29,0,360,227,13,0,321,247,29,0,289,75,19,1')">Wei:do;act. become</option>
        <option value="javascript:tongram('Jie:tie;knit;knot;weave. congeal;form',76,'332,224,0,1,221,174,4,0,348,192,28,0,388,242,28,0,363,152,0,0,343,172,0,1,343,112,0,0,323,172,0,0,303,167,8,0,383,157,8,0,265,175,20,0,374,312,12,0,363,112,0,1,239,260,4,0,348,192,28,0')">Jie:tie;knit;knot;weave. congeal;form</option>
        <option value="javascript:tongram('Jing:view;scene. admire;respect',77,'287,188,24,0,290,100,24,0,287,188,28,1,328,280,6,0,286,302,22,0,311,141,28,0,296,188,0,0,332,245,0,0,320,174,8,0,312,240,24,0,252,333,6,1,295,99,0,0,332,265,8,1,277,322,6,0,315,216,12,1')">Jing:view;scene. admire;respect</option>
        <option value="javascript:tongram('Yang:sheep',78,'309,264,24,0,242,103,31,0,350,121,0,0,309,264,0,0,329,364,24,0,329,324,0,0,310,181,0,0,329,364,0,0,324,384,0,1,324,344,0,0,391,121,12,0,309,324,8,0,369,284,8,1,311,202,28,0,290,181,28,0')">Yang:sheep</option>
        <option value="javascript:tongram('Jin:gold. metal. golden',79,'322,89,28,0,209,202,4,0,309,269,28,0,419,152,10,1,284,219,12,0,309,169,0,0,352,206,20,0,309,249,0,0,366,199,20,0,289,244,24,0,265,185,8,1,349,169,0,1,369,229,24,0,382,137,2,1,309,269,28,0')">Jin:gold. metal. golden</option>
        <option value="javascript:tongram('Shuang:frost. white',80,'307,215,28,0,288,88,0,0,352,230,4,1,317,205,28,0,287,272,0,0,317,129,0,0,286,205,0,0,369,236,4,0,356,170,1,1,313,160,3,0,392,216,12,0,357,129,0,1,392,216,0,1,400,144,20,0,290,117,20,0')">Shuang:frost. white</option>
        <option value="javascript:tongram('Wei:maintain. tie up. dimension',81,'262,145,28,0,289,222,8,0,305,262,4,1,337,138,5,0,338,227,0,1,338,194,0,0,338,294,16,0,338,263,8,0,373,237,0,1,373,273,0,0,249,222,0,0,378,194,0,1,338,254,0,0,206,243,4,0,257,249,20,0')">Wei:maintain. tie up. dimension</option>
        <option value="javascript:tongram('Xing:walk. perform. behaviour',82,'268,238,0,1,297,212,0,0,376,278,4,1,196,255,12,0,391,198,28,0,238,240,12,0,419,226,12,0,267,212,12,0,401,215,20,0,255,230,12,0,406,240,12,0,295,185,4,1,225,227,20,1,358,202,12,0,305,349,12,1')">Xing:walk. perform. behaviour</option>
        <option value="javascript:tongram('Li:beautiful',83,'255,192,26,0,329,187,28,0,298,259,4,0,298,120,28,0,242,179,4,0,313,170,12,0,298,260,16,0,326,231,4,0,341,262,0,1,299,245,24,0,310,112,28,0,270,207,12,1,327,129,28,1,239,237,4,0,399,173,20,0')">Li:beautiful</option>
        <option value="javascript:tongram('Sheng:give birth to. grow. life. raw',84,'278,225,0,1,238,305,0,0,355,157,28,0,271,185,12,0,318,157,16,0,318,117,0,0,298,117,0,0,262,157,0,0,313,137,0,1,257,137,0,0,318,281,4,0,278,305,16,1,318,185,0,1,256,200,12,1,292,330,12,1')">Sheng:give birth to. grow. life. raw</option>
        <option value="javascript:tongram('Ke:restrain. overcome. gram',85,'299,82,0,0,269,147,24,0,369,290,28,0,259,122,8,0,284,290,20,0,298,276,4,0,241,304,12,0,270,304,20,0,288,293,28,0,224,315,28,0,340,318,12,0,297,190,28,0,227,290,20,1,312,205,12,0,410,248,20,0')">Ke:restrain. overcome. gram</option>
        <option value="javascript:tongram('Xian:virtuous and able',86,'243,89,0,0,284,175,8,0,309,210,4,1,323,265,7,0,337,132,12,0,322,146,4,0,326,245,12,0,329,220,4,0,338,262,8,0,393,182,28,0,271,197,4,1,313,187,0,0,393,161,20,0,296,254,28,1,322,89,28,0')">Xian:virtuous and able</option>
        <option value="javascript:tongram('Yu:jade',87,'313,189,1,0,251,213,24,0,250,292,28,1,291,216,4,0,237,279,28,0,346,307,28,0,319,279,20,0,208,306,20,0,225,296,28,0,329,296,4,0,334,321,12,0,251,292,16,1,291,173,0,1,306,131,28,1,292,145,28,1')">Yu:jade</option>
        <option value="javascript:tongram('Shui:water',88,'361,166,0,0,315,201,8,0,315,200,4,0,314,174,30,0,370,119,28,0,397,146,28,0,341,147,28,0,369,175,28,0,359,136,28,0,379,157,28,0,206,172,30,0,299,210,22,0,258,151,30,0,299,105,20,1,343,228,20,0')">Shui:water</option>
        <option value="javascript:tongram('Zuo:rise. write;compose. pretend. regard as',89,'284,183,28,0,284,217,8,0,313,245,4,1,284,273,4,0,340,282,12,0,327,314,28,0,346,232,12,0,298,342,28,0,329,264,4,0,309,325,28,0,211,241,12,0,237,157,28,0,224,170,28,0,337,164,28,1,210,241,20,1')">Zuo:rise. write;compose. pretend. regard as</option>
        <option value="javascript:tongram('Nian:think of;miss. thoght;idea',90,'263,237,1,1,320,164,30,0,296,279,2,1,272,119,8,0,315,229,0,0,307,276,20,0,315,249,8,0,303,196,12,0,295,244,8,0,317,189,4,0,258,215,12,0,284,131,28,0,271,144,28,0,400,271,3,1,357,270,18,0')">Nian:think of;miss. thoght;idea</option>
        <option value="javascript:tongram('Kun:Kunlun Mountains',91,'212,141,0,0,199,182,24,0,292,158,4,1,199,262,16,0,313,161,4,1,348,241,24,0,328,240,0,1,300,241,0,0,323,220,0,1,256,222,1,0,368,280,4,0,308,240,24,1,260,217,0,0,324,159,12,0,344,139,20,0')">Kun:Kunlun Mountains</option>
        <option value="javascript:tongram('Chu:go or come out. issue. produce',92,'307,131,0,0,287,168,24,0,307,209,4,0,336,236,0,0,287,209,0,0,356,209,0,0,287,248,0,0,356,249,0,0,292,228,0,0,351,229,0,0,276,258,20,0,276,315,8,0,296,315,8,0,398,290,20,0,356,249,4,0')">Chu:go or come out. issue. produce</option>
        <option value="javascript:tongram('De:virtue;morals. mind. kindness',93,'282,148,28,0,304,141,0,0,277,278,4,1,365,285,30,0,367,222,0,0,340,284,20,0,300,217,0,0,336,141,0,1,363,220,15,0,342,217,2,0,320,266,19,0,387,222,8,1,360,257,8,1,411,291,3,1,214,248,12,1')">De:virtue;morals. mind. kindness</option>
        <option value="javascript:tongram('Sheng:holy;sacred. saint;sage. emperor',94,'264,198,24,0,234,100,0,0,284,287,28,0,244,141,4,1,264,201,0,0,284,256,0,0,339,222,4,0,263,177,5,0,329,205,4,0,319,261,0,0,297,219,5,0,244,141,0,0,304,287,16,0,325,330,12,0,298,151,28,0')">Sheng:holy;sacred. saint;sage. emperor</option>
        <option value="javascript:tongram('Jian:sword;sabre',95,'258,89,28,0,396,173,28,1,202,145,0,0,368,145,28,0,339,202,28,0,282,230,4,0,299,200,12,0,260,211,28,0,231,196,29,1,284,123,4,0,233,159,28,0,311,174,28,0,325,188,20,1,336,230,0,0,279,222,28,1')">Jian:sword;sabre</option>
        <option value="javascript:tongram('Gang:ridge of a hill',96,'231,158,0,0,362,197,8,0,362,195,4,0,350,129,28,0,336,182,0,0,356,224,0,0,293,177,4,0,327,239,0,0,351,244,0,1,322,219,0,0,322,157,0,0,276,219,24,1,276,202,8,0,223,246,4,0,391,223,20,0')">Gang:ridge of a hill</option>
        <option value="javascript:tongram('Ming:name. fame;reputation. famous',97,'328,79,28,0,283,248,22,1,271,136,0,0,326,177,28,0,240,262,4,0,268,233,4,0,352,150,28,0,254,276,4,0,258,251,28,0,237,287,28,0,310,136,28,0,338,220,4,1,353,150,28,0,359,273,28,1,331,244,28,0')">Ming:name. fame;reputation. famous</option>
        <option value="javascript:tongram('Jian:build. establish. propose',98,'420,307,22,0,257,313,6,0,309,291,10,0,240,152,6,0,346,254,0,0,386,168,0,0,346,132,16,0,366,132,0,0,313,171,11,1,361,112,0,0,386,188,4,0,386,132,0,1,274,274,14,0,349,211,28,0,327,190,28,0')">Jian:build. establish. propose</option>
        <option value="javascript:tongram('Ju:huge;tremendous;gigantic',99,'271,230,24,0,190,92,0,0,271,230,28,1,272,230,12,0,230,210,24,0,230,190,8,0,190,209,24,0,269,190,8,0,210,215,24,0,250,195,8,0,230,150,0,1,230,210,16,0,269,150,24,0,311,163,20,0,239,123,12,1')">Ju:huge;tremendous;gigantic</option>
        <option value="javascript:tongram('Hao:name. mark. size. date. order',100,'350,123,24,0,187,191,1,0,285,226,5,1,349,234,4,0,309,235,0,1,349,270,0,0,303,221,4,0,308,294,16,0,195,284,5,0,345,212,0,0,290,235,0,0,217,210,29,0,309,295,16,1,317,207,12,0,194,130,28,0')">Hao:name. mark. size. date. order</option>
        <option value="javascript:tongram('Xing:form;shape. body;entity. appear',101,'367,143,30,0,219,158,0,0,240,215,4,0,268,299,20,0,269,299,20,0,241,271,4,0,309,271,0,0,240,271,0,0,252,288,20,0,220,266,24,0,339,258,2,0,269,189,0,0,309,309,16,0,390,291,3,1,267,189,12,1')">Xing:form;shape. body;entity. appear</option>
        <option value="javascript:tongram('Li:stand. upright. appoint. establish',102,'304,262,24,0,232,143,0,0,304,262,28,1,304,222,0,0,375,248,28,0,326,129,4,0,400,274,12,0,353,156,4,0,384,265,20,0,336,146,4,0,389,290,12,0,312,142,28,0,299,156,28,0,346,220,20,0,261,88,28,0')">Li:stand. upright. appoint. establish</option>
        <option value="javascript:tongram('Zhu:pearl. bead',103,'435,281,22,0,202,264,1,0,295,270,0,0,242,199,4,0,355,179,0,0,335,179,0,0,335,170,0,0,315,210,8,0,228,228,0,0,370,222,0,0,283,205,3,0,315,330,16,1,355,170,0,1,355,270,16,0,212,169,28,0')">Zhu:pearl. bead</option>
        <option value="javascript:tongram('Que:imperial palace. fault. lack',104,'226,163,0,0,397,167,16,0,357,207,4,0,357,207,16,0,321,254,29,0,310,183,28,0,327,210,5,0,280,254,4,0,269,197,7,0,290,290,24,0,251,131,27,0,265,215,0,0,338,193,29,0,214,255,4,0,385,235,20,0')">Que:imperial palace. fault. lack</option>
        <option value="javascript:tongram('Biao:surface. express. list. watch',105,'310,202,24,0,345,243,31,0,305,224,0,0,265,304,16,0,358,297,0,0,310,147,4,0,383,229,12,0,336,118,0,0,333,332,8,0,331,98,0,0,225,304,0,0,282,174,20,1,318,257,0,0,330,181,28,1,282,118,28,0')">Biao:surface. express. list. watch</option>
        <option value="javascript:tongram('Duan:end. hold. proper',106,'316,225,0,1,206,188,0,0,267,264,28,0,331,115,4,0,260,180,12,0,336,247,0,0,342,283,12,0,361,284,4,0,381,302,8,0,250,163,20,0,360,197,3,0,299,152,24,1,336,247,24,1,345,246,12,0,223,249,12,1')">Duan:end. hold. proper</option>
        <option value="javascript:tongram('Ye:night;evening',107,'298,238,0,1,262,155,0,0,318,178,0,0,278,218,0,0,382,304,4,0,325,276,4,0,341,240,4,0,340,262,28,0,364,212,12,0,399,315,20,0,278,258,8,1,382,191,28,0,396,290,20,0,360,135,28,1,291,101,28,0')">Ye:night;evening</option>
        <option value="javascript:tongram('Cheng:call. state. weigh',108,'378,143,24,0,210,195,0,0,316,236,4,1,238,222,30,0,329,237,8,0,288,235,0,0,248,195,0,0,376,257,0,1,375,185,4,0,308,187,28,0,319,277,28,0,376,238,8,1,316,237,24,1,262,132,28,1,268,217,20,0')">Cheng:call. state. weigh</option>
        <option value="javascript:tongram('Kong:empty. sky. vacant. spare time',109,'172,174,4,0,286,174,20,0,286,174,28,1,319,280,28,0,275,237,4,0,309,143,4,0,291,339,20,0,289,252,4,0,320,358,12,0,271,241,4,0,293,274,2,0,369,230,12,1,319,281,28,0,342,172,28,1,280,115,28,0')">Kong:empty. sky. vacant. spare time</option>
        <option value="javascript:tongram('Zheng:straight. upright. correct',110,'297,207,0,0,262,172,0,0,276,237,4,1,328,256,0,0,279,296,0,0,318,296,0,0,279,335,0,0,318,335,0,0,284,316,0,0,314,316,0,0,328,240,28,0,318,335,8,0,338,334,8,0,279,296,0,1,308,205,12,1')">Zheng:straight. upright. correct</option>
        <option value="javascript:tongram('Guo:fruit. result. as expected',111,'343,109,0,0,330,237,30,0,324,190,28,0,265,297,16,0,303,140,0,1,303,110,0,0,303,190,0,0,303,170,8,0,323,185,8,1,338,151,0,0,225,297,0,0,284,318,16,1,303,170,16,1,241,142,20,1,264,258,0,0')">Guo:fruit. result. as expected</option>
        <option value="javascript:tongram('Guang:light. scenery. honor. glossy',112,'319,97,0,0,377,263,4,0,394,263,0,0,333,245,4,1,372,141,28,0,277,275,28,0,386,155,4,0,320,261,4,0,376,139,4,0,260,286,28,0,261,126,27,0,333,162,28,0,263,261,20,1,334,263,0,0,263,177,28,0')">Guang:light. scenery. honor. glossy</option>
        <option value="javascript:tongram('Chuang:pass. pass on. spread',113,'360,152,24,0,175,238,4,0,272,181,8,1,319,203,28,0,391,211,20,0,312,299,28,0,350,154,0,1,298,312,4,0,278,285,28,0,345,135,0,0,322,306,0,0,334,208,28,1,362,151,28,0,274,277,4,1,361,306,28,1')">Chuang:pass. pass on. spread</option>
        <option value="javascript:tongram('Gu:valley. cereal;grain',114,'268,145,0,1,293,368,4,0,376,188,0,0,268,225,16,0,320,255,28,0,280,239,12,0,293,282,28,0,306,213,12,0,309,272,28,0,296,230,12,0,350,368,4,0,280,239,28,1,333,328,12,0,416,188,16,0,376,227,28,1')">Gu:valley. cereal;grain</option>
        <option value="javascript:tongram('Li:plum',115,'281,113,26,0,241,258,0,0,279,204,28,0,320,162,28,0,308,83,0,0,268,83,0,0,288,143,24,0,308,83,0,0,328,88,24,0,303,63,0,0,281,258,0,0,268,83,0,0,320,161,28,0,345,164,11,0,308,206,28,0')">Li:plum</option>
        <option value="javascript:tongram('Zhen:treasure. precious. rare',116,'332,99,29,0,342,154,31,0,288,166,1,0,370,215,6,0,206,166,0,0,226,124,0,0,246,224,24,0,225,185,8,0,191,229,0,0,261,183,0,0,331,240,4,0,266,124,0,1,246,184,0,1,272,233,3,0,359,212,20,0')">Zhen:treasure. precious. rare</option>
        <option value="javascript:tongram('Xu:emptiness. humble. abstract',117,'262,117,0,1,246,284,0,0,325,89,28,0,273,232,0,1,332,197,16,0,354,257,28,0,297,138,0,0,297,158,0,0,312,193,8,0,277,153,24,0,272,233,0,0,297,177,8,0,297,257,20,1,399,150,20,0,297,312,12,1')">Xu:emptiness. humble. abstract</option>
        <option value="javascript:tongram('Sheng:sound. tone. reputation',118,'266,59,0,0,210,139,0,0,265,291,8,0,327,178,0,1,274,175,28,0,194,206,4,0,278,206,12,0,266,119,0,0,243,220,4,0,246,114,24,0,309,217,28,0,264,193,4,1,284,192,20,1,277,303,28,1,244,230,28,0')">Sheng:sound. tone. reputation</option>
        <option value="javascript:tongram('Cai:vegetable. dish;course',119,'371,253,26,1,285,211,0,0,298,277,0,0,334,139,3,0,358,138,30,0,378,175,28,0,339,211,0,0,358,139,30,0,287,154,9,0,311,185,5,0,314,164,6,0,394,155,14,0,358,277,0,1,358,277,16,0,338,237,12,1')">Cai:vegetable. dish;course</option>
        <option value="javascript:tongram('Nai:a kind of apple',120,'310,176,25,0,258,131,0,0,331,245,28,0,300,277,30,0,344,231,28,0,314,203,0,0,372,259,12,0,335,131,0,0,354,248,20,0,330,111,0,0,372,326,7,0,294,333,16,1,314,163,0,0,370,223,11,0,305,163,12,1')">Nai:a kind of apple</option>
        <option value="javascript:tongram('Xi:practise;review. be used to. habit',121,'247,255,0,1,153,193,1,0,246,135,28,0,234,204,16,0,344,158,28,0,288,186,4,0,316,185,28,0,267,276,0,0,333,175,28,0,274,290,3,0,250,316,28,0,259,213,20,1,307,316,16,0,218,220,4,0,273,143,28,0')">Xi:practise;review. be used to. habit</option>
        <option value="javascript:tongram('Tang:the main room of a house. hall',122,'232,131,1,0,273,241,0,0,307,281,28,0,302,119,27,0,303,241,8,0,303,146,0,0,342,201,0,1,323,126,0,0,318,106,0,1,323,196,24,0,326,142,2,0,282,181,24,1,363,166,8,1,346,323,12,0,363,202,4,0')">Tang:the main room of a house. hall</option>
        <option value="javascript:tongram('Jie:mustard',123,'299,183,27,0,299,335,8,0,233,228,31,0,280,306,22,0,312,163,29,0,270,146,10,0,311,164,5,0,278,164,10,0,355,103,21,0,223,171,18,0,301,255,18,0,242,179,14,1,344,112,29,0,347,225,29,0,297,192,19,1')">Jie:mustard</option>
        <option value="javascript:tongram('Zhong:weigh. heavy',124,'297,194,0,1,230,126,0,0,329,274,28,0,336,194,4,0,364,179,28,0,281,174,10,0,317,127,0,0,317,254,0,0,378,173,20,0,270,185,18,0,337,154,12,0,297,214,16,1,337,214,0,1,321,66,28,1,309,126,28,0')">Zhong:weigh. heavy</option>
        <option value="javascript:tongram('Huo:misfortune;disaster;calamity. ruin',125,'321,165,0,1,215,135,0,0,245,184,4,0,290,103,27,0,343,134,0,0,274,163,0,1,336,211,4,0,358,184,0,0,367,195,20,0,338,179,24,0,341,224,28,0,342,100,0,0,398,223,16,0,296,121,20,1,246,164,20,0')">Huo:misfortune;disaster;calamity. ruin</option>
        <option value="javascript:tongram('Ting:listen;hear. heed;obey',126,'255,152,0,1,349,194,8,0,375,255,28,0,309,153,8,0,273,212,20,0,328,186,4,0,390,223,6,0,235,231,8,0,315,222,4,0,280,287,24,0,376,255,2,0,255,172,0,0,234,231,24,1,277,170,12,0,371,172,20,0')">Ting:listen;hear. heed;obey</option>
        <option value="javascript:tongram('Hai:sea;ocean',127,'237,122,0,0,277,218,24,0,237,202,4,0,302,106,4,0,386,181,28,0,307,184,20,0,358,209,28,0,355,185,28,0,375,198,28,0,400,174,4,0,318,296,0,0,317,195,28,1,330,181,28,1,352,112,28,1,357,295,4,0')">Hai:sea;ocean</option>
        <option value="javascript:tongram('Jiang:ginger',128,'329,253,24,0,301,152,0,0,357,281,12,1,293,193,8,0,304,240,12,0,307,312,20,0,389,301,12,0,333,341,0,0,381,236,2,1,313,336,24,0,293,109,27,0,373,193,0,1,333,281,0,0,399,239,28,1,342,151,3,0')">Jiang:ginger</option>
        <option value="javascript:tongram('E:evil;vice;wickedness. firece;ferocious',129,'267,293,2,1,229,133,0,0,307,272,28,0,336,313,6,0,301,252,0,0,272,252,0,1,321,212,0,1,292,212,0,0,316,192,0,1,287,192,0,0,276,321,22,0,301,251,16,1,252,192,0,0,324,118,28,1,337,312,18,0')">E:evil;vice;wickedness. firece;ferocious</option>
        <option value="javascript:tongram('Yin:cause;reason. because',130,'240,173,0,0,310,297,0,0,310,216,0,0,331,197,28,0,370,257,0,0,360,252,28,0,370,297,0,0,350,157,0,0,375,277,0,0,330,152,24,0,310,216,0,0,350,197,0,0,390,197,16,0,284,320,12,1,271,221,20,0')">Yin:cause;reason. because</option>
        <option value="javascript:tongram('He:river',131,'278,208,24,0,222,147,31,0,343,138,28,1,301,247,20,0,358,199,0,0,358,239,16,0,358,199,0,0,321,208,0,0,363,219,0,0,363,314,24,0,288,138,28,0,338,299,16,1,341,188,0,1,399,137,28,1,285,243,19,0')">He:river</option>
        <option value="javascript:tongram('Xian:salty;salted',132,'320,168,2,0,334,282,6,0,300,242,5,1,299,220,10,0,315,272,3,1,226,256,12,0,335,269,16,0,260,207,28,0,320,257,8,0,320,303,24,0,385,224,5,0,225,296,16,1,225,177,0,0,362,270,2,0,225,296,4,1')">Xian:salty;salted</option>
        <option value="javascript:tongram('Fu:good fortune;blessing;happiness',133,'257,222,28,0,190,209,0,0,300,258,4,1,298,133,4,0,237,279,0,0,340,236,0,0,340,316,0,0,320,268,8,0,360,311,8,0,355,277,0,0,285,204,28,0,340,236,0,0,340,256,0,1,339,147,28,1,209,153,28,0')">Fu:good fortune;blessing;happiness</option>
        <option value="javascript:tongram('Ji:accumulate. long-pending. product',134,'312,255,8,0,217,203,0,0,245,255,4,0,277,143,0,0,316,321,28,0,334,177,4,0,340,150,0,0,257,203,0,1,317,285,4,0,385,321,3,0,279,232,28,0,307,204,20,1,330,335,12,0,203,272,4,0,307,150,28,0')">Ji:accumulate. long-pending. product</option>
        <option value="javascript:tongram('Lin:scale(of fish, etc.)',135,'402,196,24,0,216,169,6,0,349,249,4,0,198,167,7,0,358,197,8,0,320,194,20,0,348,250,4,0,292,222,20,0,306,152,6,0,255,194,2,0,340,143,5,0,263,156,29,0,306,179,28,1,235,209,29,0,330,179,11,1')">Lin:scale(of fish, etc.)</option>
        <option value="javascript:tongram('Dan:tasteless. light. without salt',136,'408,284,22,0,181,188,0,0,294,258,1,0,325,211,1,0,293,197,29,0,372,238,28,0,247,234,29,0,208,256,21,0,296,152,28,1,291,227,4,0,354,182,3,0,293,198,5,0,264,245,3,1,354,142,3,1,186,133,28,0')">Dan:tasteless. light. without salt</option>
        <option value="javascript:tongram('Shan:good;virtuous. perfect. kind',137,'321,54,31,0,205,222,0,0,284,222,28,1,304,96,27,0,305,202,0,1,285,182,0,1,305,202,24,0,305,162,0,0,340,192,0,1,285,157,24,0,298,279,28,0,345,142,0,1,355,279,28,0,339,221,28,1,298,262,20,0')">Shan:good;virtuous. perfect. kind</option>
        <option value="javascript:tongram('Yuan:reason. edge;fringe. along. predestined relationship',138,'326,266,26,1,195,214,4,0,296,292,4,0,238,214,4,1,280,193,12,0,296,330,0,0,282,249,20,0,269,292,12,0,302,163,4,1,345,258,30,0,262,213,28,0,296,235,28,0,306,213,12,0,209,299,4,0,297,235,12,1')">Yuan:reason. edge;fringe. along. predestined relationship</option>
        <option value="javascript:tongram('Yu:feather;plume',139,'303,183,8,0,297,288,4,0,354,231,4,0,296,263,28,0,243,219,13,0,324,206,28,0,227,208,21,0,316,254,28,0,229,204,5,0,306,237,20,0,197,243,29,0,257,183,0,0,257,203,0,0,255,140,28,0,342,243,8,1')">Yu:feather;plume</option>
        <option value="javascript:tongram('Qian:hidden. stealthly. dive',140,'197,102,0,0,286,281,4,0,286,224,4,1,300,100,28,0,312,243,20,0,215,191,28,0,261,100,0,0,321,97,0,1,351,173,28,1,291,175,4,0,302,97,28,0,264,121,28,0,307,188,12,0,195,238,4,0,283,199,28,0')">Qian:hidden. stealthly. dive</option>
        <option value="javascript:tongram('Chi:ruler. a Chinese unit of length(close to foot)',141,'356,156,24,0,194,306,3,0,260,261,3,0,276,184,1,1,359,285,29,0,369,169,28,0,338,318,29,0,341,198,28,0,353,304,29,0,352,181,28,0,306,230,15,0,369,224,20,0,382,182,4,1,359,285,3,1,425,329,13,0')">Chi:ruler. a Chinese unit of length(close to foot)</option>
        <option value="javascript:tongram('Qing:celebrate;congratulate',142,'307,73,31,0,226,194,0,0,321,153,28,0,298,116,27,0,332,221,28,0,343,328,4,0,262,323,20,0,314,329,28,0,297,353,4,0,354,345,4,0,332,223,12,0,289,267,28,0,318,353,12,0,345,236,12,0,262,240,28,0')">Qing:celebrate;congratulate</option>
        <option value="javascript:tongram('Long:Dragon',143,'219,230,0,1,155,198,0,0,275,226,4,0,259,145,28,0,329,237,20,0,234,139,20,0,238,279,12,0,234,169,12,0,323,218,8,0,244,313,24,0,267,222,12,0,219,238,0,0,279,162,24,1,316,267,4,0,345,296,12,0')">Long:Dragon</option>
        <option value="javascript:tongram('Xiang:circle in the air',144,'408,173,0,0,277,167,6,0,407,213,8,1,288,135,27,0,274,203,0,1,274,173,0,0,294,233,24,0,254,233,8,0,396,252,13,0,309,214,0,0,323,201,28,0,314,173,0,1,294,293,16,0,325,253,4,0,431,230,20,0')">Xiang:circle in the air</option>
        <option value="javascript:tongram('Fei:wrong. not conform to. non-',145,'248,167,0,0,296,218,8,0,296,218,4,0,247,188,27,0,348,177,28,0,216,262,12,0,341,271,20,0,230,276,4,0,371,290,12,0,214,266,4,0,186,219,28,0,362,164,28,0,369,214,28,0,282,288,4,0,281,214,20,0')">Fei:wrong. not conform to. non-</option>
        <option value="javascript:tongram('Bi:a round flat piece of jade with holes in center',146,'315,322,24,0,196,255,4,0,266,157,28,0,253,226,16,0,275,302,0,0,263,255,0,0,345,224,0,0,365,199,0,0,310,208,0,0,338,289,4,0,346,188,12,0,275,262,0,0,345,188,0,1,305,230,20,0,283,353,12,1')">a round flat piece of jade with a hole in center</option>
        <option value="javascript:tongram('Huo:fire',147,'310,211,27,1,197,273,3,0,376,255,29,0,251,285,15,0,299,255,15,0,267,210,7,0,259,248,31,0,306,218,7,0,268,230,31,0,298,236,31,0,323,140,11,0,282,131,31,0,278,150,31,0,264,184,2,1,313,204,2,0')">Huo:fire</option>
        <option value="javascript:tongram('Shi:teacher. example. troops;army. division',148,'210,167,0,0,297,137,0,0,324,216,4,1,238,275,20,0,346,300,0,0,366,260,0,0,346,200,0,0,366,300,0,0,391,275,8,0,361,280,0,0,406,164,12,0,326,200,0,0,406,259,16,0,294,223,4,1,224,153,28,0')">Shi:teacher. example. troops;army. division</option>
        <option value="javascript:tongram('Cun:a Chinese unit of length(close to inch)',149,'288,86,0,0,177,179,0,0,317,259,4,1,315,139,0,0,260,251,28,0,297,179,0,0,274,265,4,0,297,219,0,0,264,248,4,0,292,199,0,0,317,259,8,0,377,179,24,0,357,179,24,0,289,286,28,1,217,219,0,0')">Cun:a Chinese unit of length(close to inch)</option>
        <option value="javascript:tongram('Bao:treasure. precious',150,'218,136,0,1,223,281,0,0,276,157,28,0,256,102,5,0,212,235,0,0,282,215,0,0,282,215,0,0,268,261,28,0,325,252,29,1,237,200,8,0,300,365,6,0,322,215,0,1,252,215,0,1,318,199,12,0,204,347,12,1')">Bao:treasure. precious</option>
        <option value="javascript:tongram('Niao:bird',151,'298,63,28,0,270,175,28,0,298,147,4,1,252,247,28,0,263,264,12,0,319,146,0,0,288,261,4,0,317,125,4,0,318,244,20,0,319,178,2,0,371,219,20,0,299,86,0,0,319,206,16,1,342,246,28,1,301,206,28,0')">Niao:bird</option>
        <option value="javascript:tongram('Di:god. emporer',152,'255,189,0,1,242,146,0,0,312,213,28,0,274,317,20,0,334,301,0,1,314,321,0,0,294,261,0,0,287,205,28,0,309,341,0,1,331,202,29,0,304,145,6,0,274,261,0,0,354,321,16,0,354,255,12,0,289,178,12,1')">Di:god. emporer</option>
        <option value="javascript:tongram('Shi:correct;right. yes. be',153,'258,233,30,0,324,290,30,0,325,291,26,0,327,169,28,0,302,249,0,1,287,109,0,0,242,229,0,1,294,146,4,0,317,225,0,1,222,224,24,0,192,293,30,0,287,109,0,0,242,209,24,1,227,146,20,1,346,212,20,0')">Shi:correct;right. yes. be</option>
        <option value="javascript:tongram('Yin:the moon. negative',154,'316,125,0,0,344,178,30,0,248,217,4,1,276,148,28,0,354,241,21,0,307,266,0,0,336,314,28,0,352,217,5,0,367,296,20,0,312,211,8,0,276,206,0,0,347,286,16,0,276,148,28,0,262,288,4,1,351,328,12,0')">Yin:the moon. negative</option>
        <option value="javascript:tongram('Ren:human being;person;people',155,'316,91,1,0,399,279,14,0,341,212,30,0,429,249,26,0,256,214,29,0,294,192,29,0,288,236,13,0,273,225,29,0,269,230,21,0,279,206,29,0,435,310,10,0,288,164,29,0,452,303,18,0,245,230,27,1,321,114,21,0')">Ren:human being;person;people</option>
        <option value="javascript:tongram('Guan:government official',156,'262,145,0,1,277,293,8,0,326,165,28,0,303,267,0,0,343,348,16,0,323,288,0,0,303,348,0,0,337,234,0,0,323,343,8,0,317,229,24,0,303,347,8,0,323,288,0,0,357,214,0,1,368,207,12,0,280,115,28,0')">Guan:government official</option>
        <option value="javascript:tongram('Zi:moeny. subsidize. supply. endowment',157,'381,212,22,0,263,156,4,0,275,255,4,1,224,127,6,0,274,324,20,0,295,248,29,0,329,353,4,0,297,270,6,0,319,336,4,0,256,334,28,0,263,206,1,0,319,253,8,1,319,253,0,1,215,194,2,0,320,185,4,0')">Zi:moeny. subsidize. supply. endowment</option>
        <option value="javascript:tongram('Jing:compete;contend',158,'356,201,24,0,194,204,0,0,377,303,9,1,299,287,7,0,274,284,28,0,247,180,4,0,324,177,20,0,192,287,12,0,335,119,18,0,257,122,2,0,215,291,30,0,302,227,28,0,206,301,12,0,252,185,12,0,287,140,28,0')">Jing:compete;contend</option>
        <option value="javascript:tongram('Shi:begin;start',159,'365,94,29,0,183,152,0,0,341,213,4,1,340,263,12,0,393,189,11,0,269,231,28,0,382,172,19,0,277,223,20,0,387,170,3,0,252,241,28,0,316,160,27,0,283,160,28,0,278,137,28,0,382,249,12,0,278,80,20,1')">Shi:begin;start</option>
        <option value="javascript:tongram('Huang:grand;magnificent. emporer;sovereign',160,'272,66,30,0,193,295,0,0,289,195,28,0,316,139,28,0,262,266,0,1,267,173,4,0,282,286,0,0,262,255,0,0,242,250,8,0,281,166,4,0,301,323,12,0,316,138,28,0,262,295,16,1,258,154,21,0,273,295,28,0')">Huang:grand;magnificent. emporer;sovereign</option>
        <option value="javascript:tongram('Shi:matter;afair;thing;business',161,'340,221,0,0,223,145,0,0,295,169,0,0,303,89,4,0,320,253,0,0,300,223,0,0,300,273,0,1,300,302,0,0,280,268,8,0,280,297,24,0,263,342,28,0,340,222,0,1,300,342,16,1,321,127,28,1,281,223,12,1')">Shi:matter;afair;thing;business</option>
        <option value="javascript:tongram('Fu:father',162,'296,238,24,0,296,198,4,0,296,198,0,0,376,296,12,0,391,254,28,0,293,107,4,0,363,281,28,0,321,134,4,0,380,271,28,0,304,125,4,0,258,180,28,0,308,226,4,0,321,239,4,0,279,120,28,1,326,108,28,0')">Fu:father</option>
        <option value="javascript:tongram('Wen:character. language. culture. civilian',163,'337,281,28,1,222,173,0,0,302,173,28,1,258,277,28,0,310,225,4,0,372,186,28,0,268,295,12,0,345,214,28,0,251,305,12,0,355,197,28,0,337,281,20,0,253,281,4,0,324,296,12,1,313,165,12,0,226,225,28,0')">Wen:character. language. culture. civilian</option>
        <option value="javascript:tongram('Zhi:make;manufacture. formulate. restrict. system',164,'245,73,0,0,332,165,8,0,332,165,4,0,298,130,4,0,265,153,0,0,245,115,0,0,245,193,16,0,205,153,8,0,250,268,8,0,224,124,30,0,285,249,4,0,285,193,0,1,205,153,24,1,183,221,4,0,360,193,20,0')">Zhi:make;manufacture. formulate. restrict. system</option>
        <option value="javascript:tongram('Yue:say',165,'268,154,24,0,182,246,9,0,268,154,28,1,228,251,16,0,325,154,28,0,268,239,28,0,297,181,28,0,241,266,28,0,314,171,28,0,251,249,28,0,284,213,12,0,338,169,28,0,324,183,28,0,181,248,5,0,240,267,12,1')">Yue:say</option>
        <option value="javascript:tongram('Jun:monarch;sovereign;spuerme ruler',166,'369,122,0,0,232,299,4,0,329,242,8,1,311,261,6,0,369,202,24,0,309,122,0,0,329,202,24,0,309,182,0,0,349,206,24,0,290,177,24,0,273,202,28,0,309,202,8,0,369,122,24,0,374,270,28,1,349,246,28,0')">Jun:monarch;sovereign;spuerme ruler</option>
        <option value="javascript:tongram('Nai:be. only then',167,'313,293,20,0,204,219,4,0,204,219,0,0,285,139,4,0,285,265,4,0,313,237,4,0,312,292,4,0,340,264,4,0,302,254,28,0,323,275,28,0,340,264,8,0,312,168,12,1,242,153,20,1,242,153,12,1,341,224,12,0')">Nai:be. only then</option>
        <option value="javascript:tongram('Zi:word;character. handwriting',168,'277,116,0,1,247,234,0,0,333,131,28,0,299,262,12,0,341,191,12,0,315,302,0,0,311,181,27,0,322,198,3,0,307,184,29,1,340,337,24,0,339,133,6,0,314,321,16,1,355,177,28,0,369,220,28,0,333,132,28,0')">Zi:word;character. handwriting</option>
        <option value="javascript:tongram('Yu:give;offer;grant. get along with. and. with',169,'197,174,3,0,196,241,0,0,276,241,28,1,260,285,29,0,329,176,28,0,264,211,20,0,310,177,4,0,255,164,4,0,310,233,4,0,237,174,28,0,349,328,7,0,289,170,28,0,330,234,12,0,317,142,20,0,304,269,12,1')">Yu:give;offer;grant. get along with. and. with</option>
        <option value="javascript:tongram('Yan:tight. strict',170,'278,54,1,0,177,220,1,0,356,218,7,1,281,95,29,0,322,149,29,0,281,233,29,0,255,239,13,0,260,266,29,0,242,271,5,0,266,247,29,0,334,125,3,0,333,132,29,0,252,167,27,1,273,180,13,0,309,288,11,1')">Yan:tight. strict</option>
        <option value="javascript:tongram('Yi:clothing;clothes;garment. coating;covering',171,'383,172,30,0,352,245,31,0,270,256,0,0,250,176,8,0,313,149,28,0,290,176,8,0,350,296,24,0,330,176,8,0,325,331,8,0,310,181,8,0,270,256,0,0,290,196,0,0,310,256,0,0,372,189,20,0,284,121,28,0')">Yi:clothing;clothes;garment. coating;covering</option>
        <option value="javascript:tongram('Fu:clothes;dress. wear. take(medicine) serve. obey',172,'295,143,0,0,324,193,24,0,235,208,5,1,351,254,8,0,344,164,28,1,255,179,28,0,345,245,4,0,242,142,8,0,267,277,8,0,238,210,20,0,392,254,12,0,242,142,0,0,282,202,0,1,374,236,12,0,317,137,28,0')">Fu:clothes;dress. wear. take(medicine) serve. obey</option>
        <option value="javascript:tongram('Xiao:filial. mourning apparel',173,'302,211,28,0,217,183,0,0,297,183,28,1,279,141,8,0,379,283,20,0,339,242,20,1,312,298,4,0,340,298,20,0,369,222,20,0,362,272,4,0,342,211,16,0,354,312,20,0,353,227,28,0,367,169,28,1,318,181,4,0')">Xiao:filial. mourning apparel</option>
        <option value="javascript:tongram('Jing:respect. respectfully. offer politely',174,'336,160,31,0,376,308,16,0,376,308,0,1,263,169,27,0,218,286,2,0,230,249,4,0,315,288,4,0,258,290,4,0,238,334,4,0,207,298,30,1,216,207,28,0,272,264,28,0,272,207,28,0,305,173,19,0,307,239,28,0')">Jing:respect. respectfully. offer politely</option>
        <option value="javascript:tongram('Tui:push',175,'255,154,0,0,291,230,7,0,330,251,4,1,214,173,8,0,350,215,0,1,350,181,0,0,350,251,24,1,350,281,8,0,385,223,0,1,385,261,0,0,354,180,6,0,390,181,0,1,350,241,0,0,230,259,4,0,255,214,27,1')">Tui:push</option>
        <option value="javascript:tongram('Chang:skirt. clothing',176,'288,254,26,1,227,224,0,0,235,143,4,1,254,250,0,0,305,193,8,1,265,138,0,0,265,118,0,0,318,115,4,0,285,189,8,0,242,110,4,0,300,259,2,0,265,118,24,1,304,213,8,1,296,293,4,1,325,194,4,0')">Chang:skirt. clothing</option>
        <option value="javascript:tongram('Jie:exhaust;use up',177,'325,142,0,1,201,276,1,0,384,255,4,1,227,147,6,0,332,253,28,0,226,258,20,0,346,183,12,0,356,248,0,0,353,217,8,0,273,247,30,0,296,251,0,0,328,142,0,0,343,294,8,1,247,241,12,0,314,324,12,1')">Jie:exhaust;use up</option>
        <option value="javascript:tongram('Dang:should;ought to. work as. deserve',178,'299,117,0,0,283,270,24,0,315,197,28,0,296,164,27,0,303,303,0,1,305,245,4,0,302,329,24,1,320,270,8,0,338,311,0,1,319,238,4,0,332,186,1,0,320,270,0,0,303,290,0,0,272,225,4,1,315,197,28,0')">Dang:should;ought to. work as. deserve</option>
        <option value="javascript:tongram('Rang:give away;yield. let;allow',179,'358,197,24,0,189,163,0,0,330,121,28,0,268,130,27,0,297,197,8,0,278,282,4,0,324,197,0,0,289,173,28,0,352,160,29,1,321,93,3,0,376,308,6,0,306,225,28,0,244,188,28,0,209,280,4,0,364,281,28,1')">Rang:give away;yield. let;allow</option>
        <option value="javascript:tongram('Wei:place. throne. digit',180,'284,112,28,0,269,198,24,0,228,169,0,0,272,242,8,0,363,232,12,0,323,223,27,0,349,218,20,0,312,206,19,0,353,215,4,0,308,208,3,0,289,157,28,0,312,282,8,0,332,282,8,0,343,144,12,0,317,185,12,1')">Wei:place. throne. digit</option>
        <option value="javascript:tongram('Zhong:loyal;devoted;honest',181,'350,135,0,0,298,307,4,0,281,244,7,1,387,237,2,0,310,215,0,0,310,195,0,0,317,244,27,0,310,155,0,0,308,256,3,0,290,150,24,0,372,275,6,0,350,215,16,0,310,95,0,0,248,176,20,1,285,236,20,1')">Zhong:loyal;devoted;honest</option>
        <option value="javascript:tongram('Li:force. power;strength;ability',182,'337,112,29,0,205,277,1,0,275,222,1,0,329,297,25,0,318,243,29,0,289,199,1,0,296,276,29,0,250,207,7,1,311,263,29,0,270,208,9,0,236,231,3,1,234,230,9,0,328,298,13,0,311,215,19,1,344,168,17,0')">Li:force. power;strength;ability</option>
        <option value="javascript:tongram('You:have;possess. there is;exist',183,'299,131,24,0,352,157,16,0,273,157,0,0,280,196,4,0,267,266,4,0,320,179,0,0,295,294,4,0,332,220,4,0,284,277,4,0,315,251,20,0,308,224,20,0,320,179,0,0,360,239,0,1,368,173,12,0,283,88,28,0')">You:have;possess. there is;exist</option>
        <option value="javascript:tongram('Guo:country;nation;state',184,'222,192,0,0,331,301,16,0,349,205,4,0,363,134,28,0,272,196,20,0,295,173,1,0,294,174,1,0,266,225,28,0,293,202,20,0,343,209,1,0,223,273,28,0,333,165,31,1,266,240,23,1,335,275,4,0,349,205,4,0')">Guo:country;nation;state</option>
        <option value="javascript:tongram('Jin:exhausted;finished. to the limit. use up',185,'345,304,24,0,282,103,0,0,344,212,28,0,351,184,28,0,333,144,0,0,273,144,0,0,314,104,0,0,376,144,12,1,347,291,14,0,325,280,2,0,302,244,20,0,371,287,8,1,273,144,24,1,395,125,20,0,321,328,12,1')">Jin:exhausted;finished. to the limit. use up5</option>
        <option value="javascript:tongram('Ze:standard;norm;criterion',186,'252,193,0,1,347,223,8,0,347,223,4,0,223,165,4,0,325,205,3,0,276,195,28,0,336,222,11,0,274,219,4,0,285,271,8,0,321,207,3,0,296,320,7,0,300,196,8,1,300,196,0,1,333,292,4,0,196,301,12,1')">Ze:standard;norm;criterion</option>
        <option value="javascript:tongram('Tao:pottery;earthenware. cultivate. happy',187,'340,228,28,0,261,170,6,0,339,229,4,0,243,143,28,0,300,229,0,0,215,200,4,0,300,201,24,0,280,200,8,0,245,234,0,0,285,174,8,0,297,159,28,0,259,248,16,1,243,143,28,0,235,248,4,1,340,230,4,0')">Tao:pottery;earthenware. cultivate. happy</option>
        <option value="javascript:tongram('Yu:the name of a legendary dynasty founded by Shun',188,'307,273,24,0,237,113,24,0,304,245,28,0,309,289,7,0,262,131,0,1,262,91,0,0,267,313,12,0,288,216,12,0,250,324,12,0,297,168,0,0,258,181,20,0,262,171,8,0,322,91,24,0,301,231,12,0,322,188,4,0')">Yu:the name of a legendary dynasty founded by Shun</option>
        <option value="javascript:tongram('Lin:face;overlook. arrive. just before. copy',189,'212,144,0,0,311,208,8,0,212,223,4,0,380,257,4,0,267,170,28,0,253,267,28,0,260,263,20,0,226,294,28,0,291,280,12,0,236,277,28,0,343,253,3,0,281,157,28,0,287,206,28,0,340,300,12,1,327,192,12,1')">Lin:face;overlook. arrive. just before. copy</option>
        <option value="javascript:tongram('Ming:life. fate. order;command. assign',190,'203,172,0,1,196,132,4,0,196,132,0,0,275,160,28,0,253,252,0,0,294,229,0,0,253,290,0,0,223,212,0,0,258,271,0,0,258,304,24,0,293,249,4,0,293,192,0,1,242,192,0,1,313,124,30,0,258,101,18,1')">Ming:life. fate. order;command. assign</option>
        <option value="javascript:tongram('Diao:hang;suspend. revoke. condole',191,'362,209,0,0,262,120,0,0,331,162,28,0,256,144,4,0,302,270,0,0,322,310,0,0,302,310,0,0,362,269,8,0,307,290,0,0,317,330,0,0,262,250,0,0,302,270,8,0,362,230,8,1,342,119,28,1,277,178,20,0')">Diao:hang;suspend. revoke. condole</option>
        <option value="javascript:tongram('Tang:the Tang Dynasty.  exaggerative',192,'244,193,29,0,253,116,0,0,282,186,7,1,298,313,12,0,343,202,0,0,303,197,0,0,303,222,16,0,323,202,0,0,329,87,29,1,338,203,0,0,292,313,6,0,303,201,24,1,302,161,24,1,338,297,12,0,302,147,12,1')">Tang:the Tang Dynasty. exaggerative</option>
        <option value="javascript:tongram('Fa:send an expedition against;strike;attack. cut down',193,'277,132,29,0,269,301,9,0,235,196,1,0,414,189,26,0,375,247,2,0,328,138,18,0,389,283,2,0,407,150,4,0,387,264,2,0,405,229,2,0,341,218,10,0,332,200,2,0,346,129,30,1,367,227,30,1,444,260,18,0')">Fa:send an expedition against;strike;attack. cut down</option>
        <option value="javascript:tongram('Min:the people. folk. civilian',194,'220,120,0,0,238,118,2,0,220,200,4,0,292,185,15,0,390,262,3,0,287,211,3,0,304,201,3,0,319,192,19,0,390,303,11,0,339,184,11,0,254,177,31,0,287,211,3,0,337,250,3,0,351,98,22,0,262,214,20,0')">Min:the people. folk. civilian</option>
        <option value="javascript:tongram('Zhou:circumference. thoughtful. week. the Zhou Dynasty',195,'230,139,0,0,263,186,24,0,351,182,4,0,365,111,28,0,315,151,0,0,295,179,0,0,335,230,0,1,295,151,8,0,315,225,8,0,299,123,8,0,379,210,20,0,275,210,24,1,295,139,0,0,351,238,28,1,351,182,4,0')">Zhou:circumference. thoughtful. week. the Zhou Dynasty</option>
        <option value="javascript:tongram('Zui:crime;guilty. fault;blame',196,'240,149,3,0,278,138,0,0,320,248,4,1,291,214,12,0,381,240,14,0,261,247,21,0,383,284,22,0,283,285,29,0,309,183,30,1,341,182,31,0,335,275,4,0,272,302,13,0,388,222,30,0,321,289,4,0,342,208,4,0')">Zui:crime;guilty. fault;blame</option>
        <option value="javascript:tongram('Lu:shoe. tread on;walk on. footstep. carry out',197,'312,125,28,0,295,269,8,0,255,182,0,0,266,212,4,0,370,191,4,1,394,194,28,0,376,289,20,0,348,262,12,0,387,306,20,0,377,205,28,0,367,167,12,0,377,204,28,1,391,275,4,1,275,289,4,0,307,107,28,0')">Lu:shoe. tread on;walk on. footstep. carry out</option>
        <option value="javascript:tongram('Shen:deep. profound',198,'220,143,0,0,260,239,24,0,220,223,4,0,366,270,6,0,290,283,4,0,290,283,4,0,364,227,0,0,331,260,0,0,316,224,12,0,311,255,24,0,310,148,28,0,331,220,24,1,351,260,0,1,268,176,4,0,325,189,12,1')">Shen:deep. profound</option>
        <option value="javascript:tongram('Game Tiles(Demo Only)',0,'257,135,0,0,257,135,0,0,336,135,0,0,297,174,0,0,296,175,0,0,336,174,0,0,296,214,0,0,336,214,0,0,301,195,0,0,331,194,0,0,336,175,0,0,336,174,0,0,336,194,0,0,257,214,0,0,276,254,0,0')">Game Tiles(Demo Only)</option>
      </select><!--END-GO-GO-GO--><script language="JavaScript">
			<!--
			function PlayGame(s)
				{	var d = s.options[s.selectedIndex].value;
					window.top.location.href = d;
					s.selectedIndex = 0;
				}
			//-->
			</script>
    </form>
    </td>
  </tr>
<!--blue bottom line-->
  <tr>
    <td COLSPAN="4" BGCOLOR="#003399" width="168" height="1"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
    <td COLSPAN="9" width="829" height="1"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
  </tr>
<!--NEW EXTERNAL RED BANNER ##end######### -->
<!-- end 2Banner_default.inc-->
<!-- start 3Nav_Col_default.inc-->
  <tr><!--lefthand_margin_of_left_column_(spacer)-->
    <td ROWSPAN="7" BGCOLOR="#6699cc" width="11" height="918"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td><!--lefthand_links_and_table_of_contents-->
    <td ROWSPAN="7" VALIGN="top" BGCOLOR="#6699cc" width="137" height="918"><font FACE="Verdana, Arial, Helvetica" CLASS="8V" SIZE="1" COLOR="#000000"><br>
    <table CELLPADDING="0" CELLSPACING="0" WIDTH="131" BORDER="0">
        <tbody>
      <tr>
        <td><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=11 ></td>
        <td><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=120 ></td>
      </tr>
      <tr>
        <td COLSPAN="2"><IMG alt ="" border=0 height=10 src="1ptrans.gif" width=1 ></td>
      </tr>
      <tr>
        <td VALIGN="top" ALIGN="left"><IMG alt=* border=0 height=11 src="dsm.gif" width=11></td>
        <td><font FACE="Verdana, Arial, Helvetica" CLASS="8V" SIZE="1" COLOR="#000000"><b>About
        the Game</b></font></td>
      </tr>
      <tr>
        <td><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
        <td><font FACE="Verdana, Arial, Helvetica" CLASS="8V" SIZE="1" COLOR="#000000"><A href="default.asp#Yi Zhi Tu">Yi Zhi Tu</A></font></td>
      </tr>
      <tr>
        <td><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
        <td><font FACE="Verdana, Arial, Helvetica" CLASS="8V" SIZE="1" COLOR="#000000"><A href="default.asp#Tongram">Tongram</A></font></td>
      </tr>
      <tr>
        <td><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
        <td><font FACE="Verdana, Arial, Helvetica" CLASS="8V" SIZE="1" COLOR="#000000"><A href="default.asp#How to Play">How to Play</A></font></td>
      </tr>
      <tr>
        <td><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
        <td><font FACE="Verdana, Arial, Helvetica" CLASS="8V" SIZE="1" COLOR="#000000"><A href="default.asp#Tips">Tips</A></font></td>
      </tr>
      <tr>
        <td><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
        <td><font FACE="Verdana, Arial, Helvetica" CLASS="8V" SIZE="1" COLOR="#000000"><A href="default.asp#Feedbacks">Feedbacks</A></font></td>
      </tr>
      <tr>
        <td COLSPAN="2"><IMG alt ="" border=0 height=10 src="1ptrans.gif" width=1 ></td>
      </tr>
      <tr>
        <td VALIGN="top" ALIGN="left"><IMG alt=* border=0 height=11 src="dsm.gif" width=11></td>
        <td><font FACE="Verdana, Arial, Helvetica" CLASS="8V" SIZE="1" COLOR="#000000"><b>Online
        Gallery</b></font></td>
      </tr>
      <tr>
        <td><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
        <td><font FACE="Verdana, Arial, Helvetica" CLASS="8V" SIZE="1" COLOR="#000000"><A href="../default.asp">Home</A></font></td>
      </tr>
      <tr>
        <td><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
        <td><font FACE="Verdana, Arial, Helvetica" CLASS="8V" SIZE="1" COLOR="#000000"><A href="../zhihai/artist.asp">Artists</A></font></td>
      </tr>
      <tr>
        <td><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
        <td><font FACE="Verdana, Arial, Helvetica" CLASS="8V" SIZE="1" COLOR="#000000"><A href="../yifang/default.asp">Paintings</A></font></td>
      </tr>
      <tr>
        <td><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
        <td><font FACE="Verdana, Arial, Helvetica" CLASS="8V" SIZE="1" COLOR="#000000"><A href="../zhihai/seals.asp">Inscription</A></font></td>
      </tr>
      <tr>
        <td><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
        <td><font FACE="Verdana, Arial, Helvetica" CLASS="8V" SIZE="1" COLOR="#000000"><A href="../zhihai/default.asp">Calligraphy</A></font></td>
      </tr>
      <tr>
        <td><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
        <td><font FACE="Verdana, Arial, Helvetica" CLASS="8V" SIZE="1" COLOR="#000000"><A href="default.asp">Games</A></font></td>
      </tr>
      <tr>
        <td COLSPAN="2"><IMG alt ="" border=0 height=10 src="1ptrans.gif" width=1 ></td>
      </tr>
      <tr>
        <td><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
        <td><font FACE="Verdana, Arial, Helvetica" CLASS="8V" SIZE="1" COLOR="#000000"><A href="../8211/index.html">Other Links</A></font></td>
      </tr>
      <tr>
        <td COLSPAN="2"><IMG alt ="" border=0 height=10 src="1ptrans.gif" width=1 ></td>
      </tr>
      <tr>
        <td COLSPAN="2"><IMG alt ="" border=0 height=10 src="1ptrans.gif" width=1 ><!--To pad the bottom of the nav_col--></font></td>
      </tr>
    </table></FONT></TD><!--righthand_margin_of_left_column_(spacer)_-->
    <td ROWSPAN="7" BGCOLOR="#6699cc" width="10" height="918"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
<!--blue_rule-->
<!--one row shorter to accomodate the new blue footer-->
    <td ROWSPAN="6" BGCOLOR="#6699cc" width="10" height="872"><IMG alt ="" border=0 height=350 src="1ptrans.gif" width=1 ></td>
<!-- end 3Nav_Col_default.inc-->
    <td ROWSPAN="6" width="11" height="872"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 > </td>
<!--empty cell to left of title .gif-->
    <td VALIGN="top" ALIGN="right" width="84" height="58"><font CLASS="10V" COLOR="#000000" FACE="Verdana, Arial, Helvetica" SIZE="2"><IMG alt ="" border=0 height=15 src="1ptrans.gif" width=1 ><br>
    </font></td>
<!--(spacer)-->
    <td width="11" height="58"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
    <td width="11" height="58"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
<!--banner_column-->
    <td COLSPAN="4" VALIGN="top" width="700" height="58"><IMG alt ="" border=0 height=5 src="1ptrans.gif" width=1 ><br>
<!--page<FONT FACE="Arial, Verdana, Helvetica" SIZE=+1><B>no title</B></FONT>-->    </td><!--righthand_margin_of_right_column-->
    <td ROWSPAN="6" width="12" height="872"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 > </td></TR>
  <tr>
    <td ALIGN="left" VALIGN="top" COLSPAN="2" WIDTH="95" HEIGHT="235"><IMG alt="Tongram Image" border=0 height=74 src="jixiang4.gif" width=78></td>
    <td WIDTH="11" HEIGHT="235"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
    <td ALIGN="left" VALIGN="top" ROWSPAN="2" WIDTH="481" HEIGHT="637"><a NAME="Yi Zhi Tu"></a><IMG alt=* border=0 height=11 src="dsm.gif" width=11><font size="-1" color="#000000" face="Verdana, Arial, Helvetica"> <b>Tong's Century Old Chinese Puzzle</b></font> <br>
    <font size="-1" color="#000000" face="Verdana, Arial, Helvetica">Invented by Mr. Tong
    Xie-Geng in 1862, <b><i>Yi Zhi Tu</i></b> is an interesting Chinese
    tangram game.&nbsp; For anyone who wants to learn Chinese characters, this game will let
    you have fun and also enjoy Chinese calligraphy. It is a mind game. <b><i>Yi Zhi</i></b>
    in Chinese means <i>good exercise for your mind</i>. <a href="yizhitu1.pdf">Yi Zhi Tu volume 1</a> and <a href="yizhitu2.pdf">Yi Zhi Tu volume 2</a> were first published in 1878.</font> <p><a NAME="Tongram"></a><IMG alt=* border=0 height=11 src="dsm.gif" width=11><font size="-1" color="#000000" face="Verdana, Arial, Helvetica"> <b>State-of-the-Art Java<sup>TM</sup> Applet</b></font> <br>
    <font size="-1" color="#000000" face="Verdana, Arial, Helvetica"><b>Tongram</b>
    is a Java applet. Within a Java-ready Browser, people from all over the world can
    use&nbsp; <b>Tongram</b> to play <b><i>Yi Zhi Tu</i></b> over
    the Internet.</font> </p>
    <p><a NAME="How to Play"></a><IMG alt=* border=0 height=11 src="dsm.gif" width=11><font size="-1" color="#000000" face="Verdana, Arial, Helvetica"> <b>How to Play</b></font> <br>
    <font size="-1" color="#000000" face="Verdana, Arial, Helvetica">The rule is very simple:
    for each puzzle, the pattern is a Chinese character, you have to use all 15 game tiles to
    solve it. Each tile can be moved, rotated and flipped. In <b>Tongram</b>
    applet, you can use mouse to point on and left mouse click to select a tile, keep the left
    mouse key down to drag the tile. If it is the currently selected tile, it will be in red
    color outside the pattern or in yellow color if within the pattern. You can use left mouse
    click outside the currently selected tile to rotate it counter clockwise, right mouse
    click will make the tile rotate clockwise. Middle mouse click(for 3-key mouse) or Alt key
    + left mouse click will flip the tile.&nbsp;</font> </p>
    <p><a NAME="Feedbacks"></a><IMG alt=* border=0 height=11 src="dsm.gif" width=11><font size="-1" color="#000000" face="Verdana, Arial, Helvetica"><b>Feedbacks</b></font> <br>
    <font size="-1" color="#000000" face="Verdana, Arial, Helvetica">Any comments or
    suggestions? Please click  <A href="mailto:harrytong@yahoo.com">here</A>
    to send us an email.</font></p>
    <p>&nbsp;</p>
    <p><font size="-1" color="#000000" face="Verdana, Arial, Helvetica">If you have trouble in
    launching <b>Tongram</b> Java applet, you may need the latest version of the
    browser.</font></p>
    <p align="center">
<a href="http://www.mozilla.com/en-US/firefox/"><img border="0" alt="Get Firefox!" title="Get Firefox!" src="http://sfx-images.mozilla.org/affiliates/Buttons/88x31/get.gif"/></a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 

<a href="http://www.microsoft.com/windows/ie/download/default.asp"><IMG border="0" alt="Get the latest Internet Explorer" height=31 src="ieget_animated.gif" width=88></a>
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a href="http://browser.netscape.com/ns8"><IMG border="0" alt="Get the latest Netscape Communicator" height=30 src="netscape_now_static.gif" width=90></a></p></td>
    <td WIDTH="11" HEIGHT="235"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
    <td WIDTH="10" HEIGHT="235" BGCOLOR="#99ccff"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
    <td ALIGN="left" VALIGN="top" WIDTH="198" HEIGHT="235" BGCOLOR="#99ccff"><font size="-1" color="#000000" face="Verdana, Arial, Helvetica"><b>Looking For Tips?</b></font><a NAME="Tips"> <br>
    <font size="-1" color="#000000" face="Verdana, Arial, Helvetica">After you activate the
    applet(you have to click on something on the game panel), you can aslo use keyboard to
    play the game. Home key can highlight each tile one at a time, PgUp key will rotate the
    currently selected tile counter clockwise, PgDn key will rotate it clockwise, End key will
    flip it. Arrow keys will move the tile quickly, Shift key + Arrow key will move it
    smoothly.</font></a></td>
  </tr>
  <tr>
    <td COLSPAN="2" width="95" height="402"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
    <td width="11" height="402"><!--r of view my--><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
    <td width="11" height="402"><!--under downloads--><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
<!--determines length of light blue background of downloads:-->
    <td width="10" height="402"><IMG alt ="" border=0 height=400 src="1ptrans.gif" width=1 ></td>
    <td width="198" height="402"><IMG alt ="" border=0 height=1 src="1ptrans.gif" width=1 ></td>
</tr>
  <tr>
<!--extra row left in for later developments-->
    <td COLSPAN="7" width="806" height="69">

 
    </td>
  </tr>
<!-- start 4Footer_default.inc-->
  <tr>
<!--product drop-down-->
<!--spacer row-->
    <td COLSPAN="7" width="806" height="59"><IMG alt ="" border=0 height=10 src="1ptrans.gif" width=1 ></td>
  </tr>
  <tr>
    <td COLSPAN="7" VALIGN="center" ALIGN="left" width="806" height="59"><IMG alt ="" border=0 height=10 src="1ptrans.gif" width=1 ><!--##### drop down wuz here #####--> </td>
  </tr>
  <tr>
    <td COLSPAN="10" ALIGN="middle" BGCOLOR="#6699cc" width="839" height="46">
      <p align="left"><font COLOR="#000000" CLASS="COPY" FACE="Verdana, Arial, Helvetica" SIZE="1"><IMG alt ="" border=0 height=5 src="1ptrans.gif" width=1 ><br>
      &nbsp; Copyright © <!--#include file="../this_year.txt"--> 
      Tongram.com  All rights reserved.&nbsp;&nbsp;<br></p></FONT></td>
  </tr></TBODY></TABLE></basefont>
</body>
</html>
