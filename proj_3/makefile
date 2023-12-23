
all:
	make with_file inputs/input0.txt
	make with_file inputs/input1.txt
	make with_file inputs/input2.txt
with_file: 
	python nb.py $(filter-out $@,$(MAKECMDGOALS))
	javac GaussElimination.java
	java GaussElimination $(filter-out $@,$(MAKECMDGOALS))
	rm -rf *.class
clean:
	rm -rf ./outputs/*graph*
	rm -rf ./outputs/*.txt
	rm -rf *.class

