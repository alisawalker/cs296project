#!/usr/bin/python3.3

f=open('kungfupanda','r')

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
		print(i)



