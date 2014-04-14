#!/usr/bin/python3.3

f=open('../doc/report.tex','r')
f1=f.read()
myfile=open('../doc/report.html','w')


myfile.write('<html>')

k=f1.find("\section*{Original design vs Finished design}")
m=f1.find("\end{document}")
the_part=f1[k:m]
flag = 1
while(flag):
	k = the_part.find("\section*{")
	if(k != -1):
		the_part=the_part.replace("\section*{","<h1>",1)
		z = the_part.find("}",k)
		h = z+1
		the_part=the_part[:z]+"</h1>"+the_part[h:]
	else:
		break
while(flag):
	k = the_part.find("{\\tt")
	if(k != -1):
		the_part=the_part.replace("{\\tt","<i>",1)
		z = the_part.find("}",k)
		h = z+1
		the_part=the_part[:z]+"</i>"+the_part[h:]
	else:
		break
while(flag):
	k = the_part.find("{\it")
	if(k != -1):
		the_part=the_part.replace("{\it","<i><b>",1)
		z = the_part.find("}",k)
		h = z+1
		the_part=the_part[:z]+"</b></i>"+the_part[h:]
	else:
		break

while(flag):
	k = the_part.find("\subsection*")
	if(k != -1):
		the_part=the_part.replace("\subsection*{","<h2>",1)
		z = the_part.find("}",k)
		h = z+1
		the_part=the_part[:z]+"</h2>"+the_part[h:]
	else:
		break
while(flag):
	k = the_part.find("\includegraphics[scale = 0.1]{")
	if(k != -1):
		the_part=the_part.replace("\includegraphics[scale = 0.1]{","<img src=\"",1)
		z = the_part.find("}",k)
		h = z+1
		the_part=the_part[:z]+"\" width=\"70%\">"+the_part[h:]
	else:
		break
while(flag):
	k = the_part.find("\includegraphics[scale = 0.3]{")
	if(k != -1):
		the_part=the_part.replace("\includegraphics[scale = 0.3]{","<img src=\"",1)
		z = the_part.find("}",k)
		h = z+1
		the_part=the_part[:z]+"\" width=\"70%\">"+the_part[h:]
	else:
		break
while(flag):
	k = the_part.find("\includegraphics{")
	if(k != -1):
		the_part=the_part.replace("\includegraphics{","<img src=\"",1)
		z = the_part.find("}",k)
		h = z+1
		the_part=the_part[:z]+"\" width=\"30%\">"+the_part[h:]
	else:
		break
while(flag):
	k = the_part.find("\includegraphics[scale = 0.2]{")
	if(k != -1):
		the_part=the_part.replace("\includegraphics[scale = 0.2]{","<img src=\"",1)
		z = the_part.find("}",k)
		h = z+1
		the_part=the_part[:z]+"\" width=\"70%\">"+the_part[h:]
	else:
		break
while(flag):
	k = the_part.find("\includegraphics[width=7.7in, height=2in]{")
	if(k != -1):
		the_part=the_part.replace("\includegraphics[width=7.7in, height=2in]{","<img src=\"",1)
		z = the_part.find("}",k)
		h = z+1
		the_part=the_part[:z]+"\" width=\"70%\">"+the_part[h:]
	else:
		break
while(flag):
	k = the_part.find("\includegraphics[scale = 0.5]{")
	if(k != -1):
		the_part=the_part.replace("\includegraphics[scale = 0.5]{","<img src=\"",1)
		z = the_part.find("}",k)
		h = z+1
		the_part=the_part[:z]+"\" width=\"70%\">"+the_part[h:]
	else:
		break
while(flag):
	k = the_part.find("\\begin{itemize}")
	if(k != -1):
		the_part=the_part.replace("\\begin{itemize}","<li><ol>",1)
	else:
		break
while(flag):
	k = the_part.find("\_")
	if(k != -1):
		the_part=the_part.replace("\_","_",1)
	else:
		break
while(flag):
	k = the_part.find("\%")
	if(k != -1):
		the_part=the_part.replace("\%","%",1)
	else:
		break
while(flag):
	k = the_part.find("\&")
	if(k != -1):
		the_part=the_part.replace("\&","&",1)
	else:
		break
while(flag):
	k = the_part.find("\end{itemize}")
	if(k != -1):
		the_part=the_part.replace("\end{itemize}","</ol></li>",1)
	else:
		break
while(flag):
	k = the_part.find("\\begin{enumerate}")
	if(k != -1):
		the_part=the_part.replace("\\begin{enumerate}","<li><ol>",1)
	else:
		break
while(flag):
	k = the_part.find("\\end{enumerate}")
	if(k != -1):
		the_part=the_part.replace("\end{enumerate}","</ol></li>",1)
	else:
		break

while(flag):
	k = the_part.find("\item")
	if(k != -1):
		the_part=the_part.replace("\item","</ol>*<ol>",1)
	else:
		break
while(flag):
	k = the_part.find("\centerline")
	if(k != -1):
		the_part=the_part.replace("\centerline{","<center>",1)
		z = the_part.find("}",k)
		h = z+1
		the_part=the_part[:z]+"</center>"+the_part[h:]
	else:
		break
while(flag):
	k = the_part.find("\subsubsection*{")
	if(k != -1):
		the_part=the_part.replace("\subsubsection*{","<h3>",1)
		z = the_part.find("}",k)
		h = z+1
		the_part=the_part[:z]+"</h3>"+the_part[h:]
	else:
		break
k = the_part.find("$$")
the_part=the_part.replace("$$","<center style=\"font-size:30\">",1)
z = the_part.find("$$",k)
h = z+2
the_part=the_part[:z]+"</center>"+the_part[h:]
the_part=the_part.replace("\left","",1)
the_part=the_part.replace("\\right","",1)
the_part=the_part.replace("\sum","",1)
while(flag):
	k = the_part.find("$")
	if(k != -1):
		the_part=the_part.replace("$","<b><i>",1)
		z = the_part.find("$",k)
		h = z+1
		the_part=the_part[:z]+"</i></b>"+the_part[h:]
	else:
		break
while(flag):
	k = the_part.find("{\\bf")
	if(k != -1):
		the_part=the_part.replace("{\\bf","<b>",1)
		z = the_part.find("}",k)
		h = z+1
		the_part=the_part[:z]+"</b>"+the_part[h:]
	else:
		break
while(flag):
	k = the_part.find("\\bibliographystyle{plain}")
	if(k != -1):
		the_part=the_part.replace("\\bibliographystyle{plain}","",1)
	else:
		break
while(flag):
	k = the_part.find("\\bibliography{report}")
	if(k != -1):
		the_part=the_part.replace("\\bibliography{report}","",1)
	else:
		break

myfile.write(the_part)
myfile.write('</html>')
