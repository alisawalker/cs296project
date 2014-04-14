#!/usr/bin/python3.3

f=open('fantasticfour','r')

def ispresent(source,content):
	"""This is function to check if content is a word in the source"""
	a = source.find(content)
	if a == -1:
		return False
#sp,sp;sp,';,sp,.;start of a line instead of sp
	if (a == 0 or source[a-1] == ' ') and (a + len(content) == len(source) or source[a + len(content)] in {' ','.','\'','!'}):
		return True
	return False

def newpresent(source,li):
	"""This function checks if any of li is present or not. li is list of not-to-be-present words. Returns true if source is good."""
	toreturn = False
	i = 0
	while i < len(li):
		if ispresent(source,li[i]):
			return False
		i+=1
	return True




l=[]

def add(s):
	i=0
	while i < len(s):
		#		print('algo')
		if s[i] not in (' ','\n','\t'):
			l.append(s[i:len(s)-1])
			break
		if s[i]=='\n':
			break
		i=i+1

while True:
	s=str(f.readline())
	if(s == ''):
		break
	i=0
	count_alpha=0
	count_capital=0
	while i < len(s):
		if str(s[i]).isupper():
			count_capital=count_capital+1
		if str(s[i]).isalpha():
			count_alpha=count_alpha+1
		if s[i]=='\n':
			break
		i= i+1
	if count_alpha==count_capital:
		#	print('bulog')
		add(s)
#	if len(l)!=0:
#		print('sldigh')
#		if not newpresent(l[len(l)-1],['END','End']):
#			print('pl')
#			break


def makeValid(line):
	if not newpresent(line,['INT','EXT','ROOFTOP','SCENE','SCREEN','OPEN','DRAFT','NARRATOR','INCLUDE','END','OTHERS','AT','--','BACK','TRAIN','EVERYTHING','CONTINUOUS','ON']):
		return ''
	if line.find(',') != -1 or line.find('?') != -1:
		return ''
	if ':' in line or '!' in line:
		return ''
	if line[len(line)-1] in {'.',':'}:
		return ''
	if line[0] in {'0','1','2','3','4','5','6','7','8','9'}:
		return ''
	a = line.find('(')
	if a == -1:
		return line
	if a == 0:
		return ''
	return line[:a-1]

#l.sort()
original = []
for i in l:
	q=makeValid(i)

	if ispresent(q, ' AND '):
		a = q.find(' A')
		original.append(q[0:a])
		original.append(q[a+5:])
	if q!='':
		original.append(q)

	
original.sort()
toPrint = []
for i in original:
	if i not in toPrint:
		toPrint.append(i)




#-------------------------------------------------------------------------------------------------------------------------------



#Modifies the names of characters in the list to the required format

org1=toPrint
for s in toPrint:
	q=''
	i=0
	convert=0 # means dont do anything to the first letter
	if s[0]=='D' and ((s[1]=='E' and s[2]==' ') or s[1]=="'"):
		i=1
		convert=1
		q='d'
		while i < len(s):

			if str(s[i]).isalpha():
				if s[i].isupper() and convert==0:
					q=q+s[i]
					convert=1
				elif s[i].isupper() and convert==1:
					q=q+str(s[i]).lower()
			else:
				q=q+s[i]
				convert=0
			i=i+1
		org1=org1+[q]	

	q=''
	i=0
	convert=0
	while i < len(s):

		if str(s[i]).isalpha():
			if s[i].isupper() and convert==0:
				q=q+s[i]
				convert=1
			elif s[i].isupper() and convert==1:
				q=q+str(s[i]).lower()
		else:
			q=q+s[i]
			convert=0
		i=i+1
	#print(q)
	org1=org1+[q]




f.seek(0,0)
no_times=[]
i_l=0
while i_l < len(org1):
	no_times=no_times+[0]
	i_l=i_l+1


def howmany(line,tofind):
	if line.find(tofind)!= -1:
		return 1+howmany(line[line.find(org1[i])+len(tofind):len(line)],tofind)
	else:
		return 0


while True:
	s=str(f.readline())
	i=0
	if(s == ''):
		break
	while i < len(org1):
		if s.find(org1[i])!= -1:
			no_times[i]=no_times[i]+howmany(s,org1[i])
		i= i+1

#for i in no_times:
#	print(i)


org2=tuple(org1)
org2=list(org2)
i_l=0
while i_l<len(org2):
	 org2[i_l]=org2[i_l].upper()
	 i_l=i_l+1


final_no_times=[]
i_l=0
while i_l < len(toPrint):
	final_no_times=final_no_times+[0]
	i_l=i_l+1

i_f=0
while i_f<len(org2):
	p=toPrint.index(org2[i_f])
	final_no_times[p]=no_times[i_f]+final_no_times[p]
	i_f=i_f+1

#i_f=0
#while i_f<len(toPrint):
#	print (toPrint[i_f]+' '+str(final_no_times[i_f]),end='\n')
#	#print(final_no_times[i_f],end='\n')
#	i_f=i_f+1




Nitish_list=org1[len(toPrint):len(org1)-1]
i_n=0
while i_n<len(Nitish_list):
	print(Nitish_list[i_n])
	i_n=i_n+1





f.close()

