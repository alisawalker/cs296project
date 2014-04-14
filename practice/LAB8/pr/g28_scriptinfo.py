#!/usr/bin/python3.3

p=input("Name of the text file:")
f=open(p,'r')

var = 0
while True:
	s=str(f.readline())
	i=0
	while True:
		if s[i] not in (' ','\n','\t'):
			print(s[i:],end='')
			var = 1
			break
		if s[i]=='\n':
			break			
		i=i+1
	if var == 1:
		break

var=0
while True:
	s=str(f.readline())
	i=0
	while True:
		if s[i] not in (' ','\n','\t'):
			if s[i:] not in ('Written by\n','by\n','By\n','Written By\n'):
				print(s[i:],end='')
				var=1
				break
			else:
				break
		if s[i] == '\n':
			break			
		i=i+1
	if var == 1:
		break


#for line in f:
#	print(line, end='')

f.close()



