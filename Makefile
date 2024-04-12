.DEFAULT_GOAL := build-run

run-dist:
	make -C app run-dist

test:
	make -C app test

report:
	make -C app report

build-run: build run

.PHONY: build
