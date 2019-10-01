CXXFLAGS=-std=c++11 -Wall -Wextra -Werror -pedantic -g

testcases: runCPP runJAVA

gen-randomMultiEdge: gen-randomMultiEdge.cpp

runCPP:	gen-randomMultiEdge compileJAVA
	./gen-randomMultiEdge > randomMultiEdgeMax.in
	java CavesEgor < randomMultiEdgeMax.in > randomMultiEdgeMax.out

compileJAVA: 
	javac CavesTestCreator.java
	javac CavesEgor.java

runJAVA: compileJAVA
	java -Xss8m CavesTestCreator
