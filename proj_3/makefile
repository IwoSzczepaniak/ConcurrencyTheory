all:
	python nb.py
with_file:
	python nb.py $(filter-out $@,$(MAKECMDGOALS))
jav:
	javac GaussElimination.java
	java GaussElimination

clean:
	rm -rf *graph*

