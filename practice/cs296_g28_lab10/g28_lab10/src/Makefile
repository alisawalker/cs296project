JAVAF := $(wildcard ./cs296MovieAnalysisPackage/*.java)
CLASSF := $(JAVAF:./cs296MovieAnalysisPackage/%.java=./cs296MovieAnalysisPackage/%.class)

.PHONY: clean

compile: $(CLASSF)
	@javac ./MovieAnalysisG28.java 
$(CLASSF): ./cs296MovieAnalysisPackage/%.class : ./cs296MovieAnalysisPackage/%.java
	@javac $<
doc:
	@javadoc -d cs296MovieAnalysisPackage cs296MovieAnalysisPackage/*.java
clean:
	@rm -f ./cs296MovieAnalysisPackage/*.class
	@rm -f *.class
	@cd cs296MovieAnalysisPackage; rm -rf ./cs296MovieAnalysisPackage; rm -rf ./resources; rm -f *.html; rm -f *.css; rm -f package-list 	

