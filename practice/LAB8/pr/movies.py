#!/usr/bin/python3.3

p=input("Name of the text file:")

def file_len(fname):
    with open(fname) as f:
        for i, l in enumerate(f):
            pass
    return i + 1

nooflines=file_len(p)

f = open('kungfupanda','r+')



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
#		print(i)

#
############################Character's code ##################################


org1=tuple(toPrint)
org1 = list(org1)
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


Nitish_list=org1[len(toPrint):len(org1)-1]
i_n=0
while i_n<len(Nitish_list):
#	print(Nitish_list[i_n])
	i_n=i_n+1



####################################  The honorary code ###################################

f.seek(0,0)
no_times=[]
no_times_villain=[]
i_l=0
while i_l < len(org1):
	no_times=no_times+[0]
	no_times_villain=no_times_villain+[0]
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

###############################################################################


i_f=0
while i_f<len(toPrint):
	print (toPrint[i_f]+' '+str(final_no_times[i_f]),end='\n')
	#print(final_no_times[i_f],end='\n')
	i_f=i_f+1





#############################VillainCode###########################################
f.seek(0,0)

start=nooflines*3//4
j=0
while True:
	s=str(f.readline())
	if j>=start:
		i=0
		if(s == ''):
			break
		while i < len(org1):
			if s.find(org1[i])!= -1:
				no_times_villain[i]=no_times_villain[i]+howmany(s,org1[i])
			i= i+1
	j=j+1

org3=tuple(org1)
org3=list(org3)
i_l=0
while i_l<len(org3):
	 org3[i_l]=org3[i_l].upper()
	 i_l=i_l+1


villain_no_times=[]
i_l=0
while i_l < len(toPrint):
	villain_no_times=villain_no_times+[0]
	i_l=i_l+1

i_f=0
while i_f<len(org3):
	p=toPrint.index(org2[i_f])
	villain_no_times[p]=no_times_villain[i_f]+villain_no_times[p]
	i_f=i_f+1







####################   Gender detection code    ################################
#f.seek(0,0)
#genderList = ['Undetermined']*len(toPrint)
#source = f.read()
#
#def nextword(text, index):
#	"""This gives the index of next word. Initially index is at g in 'the golf counter'. Next index will be at c in 'counter'"""
#	i = 1
#	while True:
#		if index+i <= len(text) and text[index+i] == ' ':
#			return index+i+1
#		if text[index+i] == '.':
#			return index+i+2
#		i+=1
##source = 'BARTENDER and his friends have TOM and his friends'
#index = 0
#present = ''
#while index < len(source):
#	word = source[index:nextword(source,index)-1]
#	count = toPrint.count(word)
#	if count == 0:
#		if present != '':
#			if word in {'he','He','his','His'}:
#				genderList[toPrint.index(present)] = 'Male'
#			if word in {'she', 'She', 'her', 'Her'}:
#				genderList[toPrint.index(present)] = 'Female'
#		index = nextword(source,index)
#		continue
#	present = word
#	index = nextword(source,index)
#
#def prevWord(reference, index):
#	"""This gives the index of word that is just before the given index. This doesn't take care of the case if there is no name before it
#	Suppose, the string is 'hello this a good world' and the index is initially on w of world, this func will return index on g in good and on d in    good"""
#	if reference[index-1] in {' ','.'}:
#		return prevWord(reference, index-1)
#	i = 1
#	while True:
#		if index == i -1 or reference[index-i] in {'.',' '}:
#			return [index-i+1,index-1]
#		i+=1
#
#
#
#def firstword(reference,beforeWhich,gender):
#	"""This gives the first name in reference before the occurence of the word beforeWhich"""
#	beforecopy = beforeWhich
#	while True:
#		while True:
#			pIndex = reference.find(beforeWhich)
#			l = prevWord(reference,pIndex)
#			nextfindex = l[0]
#			nextsindex = l[1]
#			b = reference[nextfindex:nextsindex+1]
#			count = toPrint.count(b.upper())
#			
#			if count != 0:
#				count = toPrint.index(b.upper())
#				genderList[count] = gender
#				break
#			if count == 0:
#				beforeWhich = b
#		reference = reference[(reference[reference.find(beforecopy):]).find(' ')+ reference.find(beforecopy)+1:]
#
#firstword('Miller and his book','his','Male')
#firstword('King and his meteorite','His','Male')
#
#print(toPrint)
#
def cutit(string, index):
	#	print(index)
	#	print('kii')
	if index >= len(string):
		return -1
	if string[index].isalpha():
		return index
	return cutit(string,index+1)


#def nextword(text, index):
#	"""This gives the index of next word. Initially index is at g in 'the golf counter'. Next index will be at c in 'counter'"""
#	i = 1
#	while True:
#		if index+i >= len(text) or text[index+i] in ['\n',' ']:
#			return index+i+1
#		if text[index+i] in ['.',',']:
#			return index+i+2
#			  
#
#		i+=1
#
def nextword(text, index):
	#	print('igh', index)
	i = 0
	if index+i == len(text):
		return -1 
	else:
		while text[index+i].isalpha():
			i+=1
		#	if index == 235781:
				#	print(index+i)
			if index+i >= len(text):
				return -1
	#	if index == 235781:
			#print(index+i)
		return cutit(text,index+i)

#toPrint = ['BARTENDER','GALEN','MARY LEE', 'MILLER', 'TOM']
#genderList = ['Undefined']*5
#source = """BARTENDER and his friends have GALEN and his friends
#and TOM,                  
#what do you think MARY LEE him
#she is      a very polite woman"""
#genderList = ['Undetermined']*len(toPrint)
genderList = [0]*len(toPrint)
f.seek(0,0)
source = f.read()
present = ''
def giveword(string, index):
	#	print(index)
	firstend = nextword(string, index)
	if firstend == -1:
		return ''
	firstend = firstend - 1
	while not string[firstend].isalpha():
		firstend = firstend - 1
	return string[index:firstend+1]
index = 0
#print(len(source))
while index < len(source):
	#	print(index)
	word = giveword(source, index)#source[index:nextword(source,index)-1]
#	print(word)
#	print(word)
	if word == '':
		break
#	if word[len(word) - 1] in {',','.'}:
#		word = word[:len(word) - 1]
	default = -1
	for i in range(len(Nitish_list)):
		if word in Nitish_list[i]:
			default = i
			break

	count = Nitish_list.count(word)
#	print(word)
#	print(word)
	if default == -1:
		#	if count == 0:
		if present != '':
			if word in {'he','He','his','His','Him','him'}:
				#				print(word)
				#print(present)
				genderList[toPrint.index(present)]+=1
			if word in {'she', 'She', 'her', 'Her'}:
				#print(word)
				#print(present)
				genderList[toPrint.index(present)]-=1
		index = nextword(source,index)
		#continue
	else:
		present = Nitish_list[default].upper()
		index = nextword(source,index)
#print(genderList)
#print(len(source))
k = 0
a = genderList[:]
toreturn = []
while k < len(a):
	if a[k] > 0:
		toreturn.append('Male')
	if a[k] < 0:
		toreturn.append('Female')
	if a[k] == 0:
		toreturn.append('Undetermined')
	k+=1
print(toreturn)#this is gender list
f.close()
#print(len(toPrint))# this is the original name list
#print(len(final_no_times))
#final_no_times contains the number of times each character appears
maxm = ['',-1]
maxw = ['',-1]
for i in range(len(toreturn)):
	if toreturn[i] == 'Male':
		if final_no_times[i] > maxm[1]:
			maxm = [toPrint[i],final_no_times[i]]
	if toreturn[i] == 'Female':
		if final_no_times[i] > maxw[1]:
			maxw = [toPrint[i],final_no_times[i]]

print('Hero:  ' + maxm[0])
print('Heroine:   ' + maxw[0])









