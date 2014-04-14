#def fib(n):
#	"""This is the fibonacci sequence calculating n fib numbers"""
#	a, b = 0, 1
#	for x in range(n):
#		print(b, end=' ')
#		a, b = b, a+b
#	print()
#
#def fibl(n):
#	"""This does the same job as above one but returns a list instead"""
#	toreturn = []
#	a, b = 0, 1
#	for x in range(n):
#		toreturn.append(b)
#		a, b = b, a+b
#	return toreturn
#
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


def makeValid(line):
	if not newpresent(line,['INT','EXT','ROOFTOP','SCENE','SCREEN','OPEN','DRAFT','NARRATOR','INCLUDE']):
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




