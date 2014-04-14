#!/usr/bin/python3.3

f=open('fantasticfour','r')


Action = ['gun','wound','freez','soldier','sword','kung fu','fight','explod','warrior']
#A_count=[]
Comedy= ['laugh','comedy','hillarious','silly','imitat','fun']
#C_count=[]
Romance = ['kiss', 'sex', 'love you', 'lov ','romance', 'heart', 'breakup']
#R_count=[]
Horror = ['ghost', 'horror', 'fear', 'scared','scaring']
#H_count=[]
Sci_fi = ['alien', 'future', 'space', 'spaceship', 'clon', 'machi']
#S_count=[]
Fantasy= ['vampire','werewolf','werewolves','magic','spell','witch','wizard','wand']
#F_count=[]

List=Action+Comedy+Romance+Horror+Sci_fi+Fantasy


Count=[]

i_c=0
while i_c < len(List):
	Count=Count+[0]
	i_c=i_c+1



def howmany(line,tofind):
	p=line.find(tofind)
	if p != -1:
		if p ==0:
			return 1+howmany(line[p+len(tofind):len(line)],tofind)
		elif s[p-1]==" ":
			return 1+howmany(line[p+len(tofind):len(line)],tofind)
		else:
			return howmany(line[p+len(tofind):len(line)],tofind)
	else:
		return 0


while True:
	s=str(f.readline())
	i=0
	if(s == ''):
		break
	while i < len(List):
		if s.find(List[i])!= -1:
			Count[i]=Count[i]+howmany(s,List[i])
		i= i+1

A_count=Count[0:len(Action)]
C_count=Count[len(Action):len(Action)+len(Comedy)]
R_count=Count[len(Action)+len(Comedy):len(Action)+len(Comedy)+len(Romance)]
H_count=Count[len(Action)+len(Comedy)+len(Romance):len(Action)+len(Comedy)+len(Romance)+len(Horror)]
S_count=Count[len(Action)+len(Comedy)+len(Romance)+len(Horror):len(Action)+len(Comedy)+len(Romance)+len(Horror)+len(Sci_fi)]
F_count=Count[len(Action)+len(Comedy)+len(Romance)+len(Horror)+len(Sci_fi):len(Action)+len(Comedy)+len(Romance)+len(Horror)+len(Sci_fi)+len(Fantasy)]


def sum(l):
	i=0
	sum=0
	while i<len(l):
		sum=sum+l[i]
		i=i+1
	return sum

A_sum=sum(A_count)
C_sum=sum(C_count)
R_sum=sum(R_count)
H_sum=sum(H_count)
S_sum=sum(S_count)
F_sum=sum(F_count)

Max= max(A_sum,C_sum,R_sum,H_sum,S_sum,F_sum) 

if A_sum == Max:
	print('Action',end='\n')
if C_sum == Max:
	print('Comedy',end='\n')
if R_sum == Max:
	print('Romance',end='\n')
if H_sum == Max:
	print('Horror',end='\n')
if S_sum == Max:
	print('Sci_fi',end='\n')
if C_sum == Max:
	print('Comedy',end='\n')


print(A_sum)
print(C_sum)
print(R_sum)
print(H_sum)
print(S_sum)
print(F_sum)

