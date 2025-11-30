# Makfile for AVL Tree Experiment

SRC_DIR = src
BIN_DIR = bin
JAVAC = javac
JAVA = java
MAIN = GenericsKbAVLApp        # <-- change if your main class is different

# Find all Java files inside src directory
SOURCES := $(shell find $(SRC_DIR) -name "*.java")
CLASSES := $(SOURCES:$(SRC_DIR)/%.java=$(BIN_DIR)/%.class)

# Default: compile everything into /bin
all: $(CLASSES)

# Rule for building .class files
$(BIN_DIR)/%.class: $(SRC_DIR)/%.java
	mkdir -p $(BIN_DIR)
	$(JAVAC) -d $(BIN_DIR) $(SOURCES)

# Run main program
run:
	$(JAVA) -cp $(BIN_DIR) $(MAIN)

# Clean compiled output
clean:
	rm -rf $(BIN_DIR)/*.class

# Rebuild from scratch
rebuild: clean all
