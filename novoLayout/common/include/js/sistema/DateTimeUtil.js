var MONTH_NAMES=new Array('Janeiro','Fevereiro','Mar�o','Abril','Maio','Junho','Julho','Agosto','Setembro','Outubro','Novembro','Dezembro','Jan','Fev','Mar','Abr','Mai','Jun','Jul','Ago','Set','Out','Nov','Dez');var DAY_NAMES=new Array('Domingo','Segunda','Ter�a','Quarta','Quinta','Sexta','Sabado','Dom','Seg','Ter','Qua','Qui','Sex','Sab');function LZ(x){return(x<0||x>9?"":"0")+x}
function DateTimeUtil(){}
DateTimeUtil.dia=function(Data_DDMMYYYY){var string_data=Data_DDMMYYYY.toString();var posicao_barra=string_data.indexOf("/");if(posicao_barra!=-1)
{dia=string_data.substring(0,posicao_barra);return dia;}
else
{return false;}}
DateTimeUtil.getStringDataHoraHoje=function(){var currentTime=new Date();var month=currentTime.getMonth()+1;var day=currentTime.getDate();var year=currentTime.getFullYear();return(day+"/"+month+"/"+year+" "+currentTime.getHours()+":"+currentTime.getMinutes()+":"+currentTime.getSeconds());}
DateTimeUtil.mes=function(Data_DDMMYYYY){var string_data=Data_DDMMYYYY.toString();var posicao_barra=string_data.indexOf("/");if(posicao_barra!=-1)
{dia=string_data.substring(0,posicao_barra);string_mes=string_data.substring(posicao_barra+1,string_data.length);posicao_barra=string_mes.indexOf("/");if(posicao_barra!=-1)
{mes=string_mes.substring(0,posicao_barra);mes=Math.floor(mes);return mes;}
else
{return false;}}
else
{return false;}}
DateTimeUtil.ano=function(Data_DDMMYYYY)
{var string_data=Data_DDMMYYYY.toString();var posicao_barra=string_data.indexOf("/");if(posicao_barra!=-1)
{dia=string_data.substring(0,posicao_barra);string_mes=string_data.substring(posicao_barra+1,string_data.length);posicao_barra=string_mes.indexOf("/");if(posicao_barra!=-1)
{mes=string_mes.substring(0,posicao_barra);mes=Math.floor(mes);ano=string_mes.substring(posicao_barra+1,string_mes.length);return ano;}
else
{return false;}}
else
{return false;}}
DateTimeUtil.diferencaEmDias=function(dataInicio,dataFim){var diferencaAux=dataFim.getTime()-dataInicio.getTime();var diferenca=Math.floor(diferencaAux/(1000*60*60*24));return diferenca;}
DateTimeUtil.diferencaEmMinutos=function(dataInicio,dataFim){var diferencaAux=dataFim.getTime()-dataInicio.getTime();var diferenca=Math.floor(diferencaAux/(1000*60));return diferenca;}
DateTimeUtil.diferencaEmSegundos=function(dataInicio,dataFim){var diferencaAux=dataFim.getTime()-dataInicio.getTime();var diferenca=Math.floor(diferencaAux/1000);return diferenca;}
DateTimeUtil.formataTotalMinutosEmHHMM=function(tempoEmMinutos){var tempoInteiro=Math.floor(tempoEmMinutos/60);var tempoDecimal=tempoEmMinutos/60;var difDecimalInteiro=tempoDecimal-tempoInteiro;var minutos=difDecimalInteiro*60;minutos=Math.round(minutos);return NumberUtil.zerosAEsquerda(tempoInteiro,2)+':'+NumberUtil.zerosAEsquerda(minutos,2);}
DateTimeUtil.converteData=function(dataDDMMYYYYStr){if(dataDDMMYYYYStr==null||dataDDMMYYYYStr.length==0)return null;if(!DateTimeUtil.isValidDate(dataDDMMYYYYStr)){return null;}
var dia=DateTimeUtil.dia(dataDDMMYYYYStr);var mes=DateTimeUtil.mes(dataDDMMYYYYStr);var ano=DateTimeUtil.ano(dataDDMMYYYYStr);mes=Math.floor(mes)-1;var dateRetorno=new Date(ano,mes,dia);return dateRetorno;}
DateTimeUtil.comparaDatas=function(dataInic,dataFim,mensagem){var d1=DateTimeUtil.converteData(dataInic.value);var d2=DateTimeUtil.converteData(dataFim.value);if(d1==null||d2==null){return true;}
if(d1.getTime()>d2.getTime()){alert(mensagem);return false;}else{return true;}}
DateTimeUtil.converteHora=function(horaHHMMStr){if(horaHHMMStr==null||horaHHMMStr.length==0)return null;var hh=DateTimeUtil.hh(horaHHMMStr);var mm=DateTimeUtil.mm(horaHHMMStr);var horaRetorno=new Date(0,0,0,hh,mm,0);return horaRetorno;}
DateTimeUtil.converteMinutosEmHHMM=function(tempoEmMinutos){var tempoInteiro=Math.floor(tempoEmMinutos/60);var tempoDecimal=tempoEmMinutos/60;var difDecimalInteiro=tempoDecimal-tempoInteiro;var minutos=difDecimalInteiro*60;return NumberUtil.zerosAEsquerda(tempoInteiro,2)+':'+NumberUtil.zerosAEsquerda(minutos,2);}
DateTimeUtil.comparaHora=function(horarioInicial,horarioFinal){if((horarioInicial!='')&&(horarioFinal!='')){var horaInicial=DateTimeUtil.hh(horarioInicial);var minutoInicial=DateTimeUtil.mm(horarioInicial);var horaFinal=DateTimeUtil.hh(horarioFinal);var minutoFinal=DateTimeUtil.mm(horarioFinal);if(horaFinal<horaInicial){return false;}else if(horaFinal==horaInicial){if(minutoFinal<minutoInicial){return false;}}}
return true;}
DateTimeUtil.hh=function(Hora_HHMM){var string_hora=Hora_HHMM.toString();var posicao_barra=string_hora.indexOf(":");if(posicao_barra!=-1)
{var hh=string_hora.substring(0,posicao_barra);return hh;}
else
{return false;}}
DateTimeUtil.mm=function(Hora_HHMM){var string_hora=Hora_HHMM.toString();var posicao_barra=string_hora.indexOf(":");if(posicao_barra!=-1)
{var hh=string_hora.substr(posicao_barra+1);return hh;}
else
{return false;}}
DateTimeUtil.isDate=function(data){if(typeof(data)=='string'){return DateTimeUtil.isValidDate(data);}else{return(data&&data.toUTCString)?true:false;}}
DateTimeUtil.isValidDate=function(Data_DDMMYYYY){if(!DateTimeUtil.ano(Data_DDMMYYYY))return false;if(!DateTimeUtil.mes(Data_DDMMYYYY))return false;if(!DateTimeUtil.dia(Data_DDMMYYYY))return false;var day,month,year;day=DateTimeUtil.dia(Data_DDMMYYYY);month=DateTimeUtil.mes(Data_DDMMYYYY);year=DateTimeUtil.ano(Data_DDMMYYYY);if(month<1||month>12){return false;}
if(day<1||day>31){return false;}
if((month==4||month==6||month==9||month==11)&&(day==31)){return false;}
if(month==2){var leap=(year%4==0&&(year%100!=0||year%400==0));if(day>29||(day==29&&!leap)){return false;}}
if(year<1900){return false;}
return true;}
DateTimeUtil.isValidTime=function(horaStr){var hora=horaStr;if(hora==null||hora.length==0){return true;}
if(hora.length!=5){alert('Formato de hora inv�lido (hh:mm)');return false;}
var h=hora.substring(0,2);var m=hora.substring(3,5);if(h>23||h<0){alert('Hora inv�lida(de 00 a 23)');return false;}
if(m>59||m<0){alert('Minuto inv�lido(de 00 a 59)');return false;}
return true;}
DateTimeUtil.formatDate=function(date,format){format=format+"";var result="";var i_format=0;var c="";var token="";var y=date.getYear()+"";var M=date.getMonth()+1;var d=date.getDate();var E=date.getDay();var H=date.getHours();var m=date.getMinutes();var s=date.getSeconds();var yyyy,yy,MMM,MM,dd,hh,h,mm,ss,ampm,HH,H,KK,K,kk,k;var value=new Object();if(y.length<4){y=""+(y-0+1900);}
value["y"]=""+y;value["yyyy"]=y;value["yy"]=y.substring(2,4);value["M"]=M;value["MM"]=LZ(M);value["MMM"]=MONTH_NAMES[M-1];value["NNN"]=MONTH_NAMES[M+11];value["d"]=d;value["dd"]=LZ(d);value["E"]=DAY_NAMES[E+7];value["EE"]=DAY_NAMES[E];value["H"]=H;value["HH"]=LZ(H);if(H==0){value["h"]=12;}
else if(H>12){value["h"]=H-12;}
else{value["h"]=H;}
value["hh"]=LZ(value["h"]);if(H>11){value["K"]=H-12;}else{value["K"]=H;}
value["k"]=H+1;value["KK"]=LZ(value["K"]);value["kk"]=LZ(value["k"]);if(H>11){value["a"]="PM";}
else{value["a"]="AM";}
value["m"]=m;value["mm"]=LZ(m);value["s"]=s;value["ss"]=LZ(s);while(i_format<format.length){c=format.charAt(i_format);token="";while((format.charAt(i_format)==c)&&(i_format<format.length)){token+=format.charAt(i_format++);}
if(value[token]!=null){result=result+value[token];}
else{result=result+token;}}
return result;}