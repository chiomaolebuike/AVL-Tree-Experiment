# AVL Tree Performance Experimental Analysis

# Set working directory (adjust as needed)
setwd("C:/Users/Chioma Olebuike/OneDrive - University of Cape Town/Documents/GitHub/assignment2/assignment2/src/")

# Install and load necessary libraries
suppressWarnings(suppressMessages({
  if (!require(ggplot2)) install.packages("ggplot2", dependencies = TRUE)
  if (!require(dplyr)) install.packages("dplyr", dependencies = TRUE)
  if (!require(gridExtra)) install.packages("gridExtra", dependencies = TRUE)
}))

library(ggplot2)
library(dplyr)
library(gridExtra)

# Placeholder for AVL tree functions (Define or source them)
create_avl_tree <- function() {
  list(root = NULL, comparisons = 0)
}

insert <- function(tree, value) {
  tree$comparisons <- tree$comparisons + 1
  return(tree)
}

search <- function(tree, key) {
  tree$comparisons <- tree$comparisons + 1
  return(tree)
}

# Load GenericsKB subset with file reading
load_generics_kb_subset <- function(file_path = "GenericsKB.txt", total_size = 50000) {
  # Check if file exists
  if (!file.exists(file_path)) {
    stop(paste("File not found:", file_path))
  }
  
  # Read the file
  data <- tryCatch({
    # Try reading the file with flexible column types
    read.table(file_path, header = FALSE, stringsAsFactors = FALSE, fill = TRUE)
  }, error = function(e) {
    # If reading as table fails, try reading as lines
    tryCatch({
      lines <- readLines(file_path)
      # Split lines and handle them one by one
      data <- lapply(lines, function(line) {
        # Try converting to numeric if possible, otherwise keep as character
        values <- strsplit(line, "\\s+")[[1]]
        numeric_values <- suppressWarnings(as.numeric(values))
        # If conversion is possible, return numeric, else return the original line
        if (all(!is.na(numeric_values))) {
          return(numeric_values)
        } else {
          return(values)
        }
      })
      # Remove NULL entries and convert to matrix/data frame
      data <- do.call(rbind, data[!sapply(data, is.null)])
      return(data)
    }, error = function(e) {
      stop(paste("Unable to read file:", file_path, "Error:", e$message))
    })
  })
  
  # Flatten and convert to numeric vector (if possible)
  if (is.matrix(data) || is.data.frame(data)) {
    data <- as.vector(as.matrix(data))
  }
  
  # Remove NA values
  data <- data[!is.na(data)]
  
  # If data is larger than total_size, sample or truncate
  if (length(data) > total_size) {
    data <- data[1:total_size]
  }
  
  # If data is smaller than total_size, repeat to fill
  if (length(data) < total_size) {
    data <- rep(data, length.out = total_size)
  }
  
  return(data)
}


# AVL Tree Performance Experiment
run_avl_tree_experiment <- function(generics_kb_subset, query_subset) {
  tree_sizes <- floor(10^seq(log10(5), log10(50000), length.out = 10))
  
  results <- lapply(tree_sizes, function(size) {
    tree_data <- sample(generics_kb_subset, size)
    
    avl_tree <- create_avl_tree()
    
    insertion_comparisons <- replicate(100, {
      avl_tree <- create_avl_tree()
      for (item in tree_data) avl_tree <- insert(avl_tree, item)
      avl_tree$comparisons
    })
    
    search_comparisons <- replicate(100, {
      avl_tree <- create_avl_tree()
      for (item in tree_data) avl_tree <- insert(avl_tree, item)
      search(avl_tree, sample(query_subset, 1))$comparisons
    })
    
    list(
      insertion = c(min = min(insertion_comparisons), max = max(insertion_comparisons), mean = mean(insertion_comparisons)),
      search = c(min = min(search_comparisons), max = max(search_comparisons), mean = mean(search_comparisons))
    )
  })
  
  insertion_df <- data.frame(size = tree_sizes, do.call(rbind, lapply(results, `[[`, "insertion")))
  search_df <- data.frame(size = tree_sizes, do.call(rbind, lapply(results, `[[`, "search")))
  
  return(list(insertion = insertion_df, search = search_df))
}

# Theoretical Complexity Calculation
calculate_theoretical_complexity <- function() {
  sizes <- floor(10^seq(log10(5), log10(50000), length.out = 10))
  
  theoretical_insertion <- data.frame(
    size = sizes,
    best_case = log2(sizes),
    average_case = sizes * log2(sizes),
    worst_case = sizes * log2(sizes)
  )
  
  theoretical_search <- data.frame(
    size = sizes,
    best_case = rep(1, length(sizes)),
    average_case = log2(sizes),
    worst_case = 2 * log2(sizes)
  )
  
  return(list(insertion = theoretical_insertion, search = theoretical_search))
}

# Prepare Data
generics_kb_subset <- load_generics_kb_subset()
query_subset <- sample(generics_kb_subset, min(100, length(generics_kb_subset)), replace = TRUE)

# Run Experiment
experimental_results <- run_avl_tree_experiment(generics_kb_subset, query_subset)

# Calculate Theoretical Complexity
theoretical_results <- calculate_theoretical_complexity()

# Plot Comparison Function
plot_comparison <- function(experimental_data, theoretical_data, title, y_label) {
  plot_data <- data.frame(
    size = experimental_data$size,
    min_comparisons = experimental_data$min,
    max_comparisons = experimental_data$max,
    mean_comparisons = experimental_data$mean,
    theoretical_best = theoretical_data$best_case,
    theoretical_avg = theoretical_data$average_case,
    theoretical_worst = theoretical_data$worst_case
  )
  
  ggplot(plot_data, aes(x = size)) +
    geom_point(aes(y = min_comparisons, color = "Experimental Min"), size = 2) +
    geom_point(aes(y = max_comparisons, color = "Experimental Max"), size = 2) +
    geom_point(aes(y = mean_comparisons, color = "Experimental Mean"), size = 2) +
    geom_line(aes(y = theoretical_best, color = "Theoretical Best"), linetype = "dashed") +
    geom_line(aes(y = theoretical_avg, color = "Theoretical Average"), linetype = "dashed") +
    geom_line(aes(y = theoretical_worst, color = "Theoretical Worst"), linetype = "dashed") +
    scale_x_continuous(trans = 'log10') +
    scale_y_continuous(trans = 'log10') +
    labs(title = title, x = "Tree Size (n)", y = y_label, color = "Legend") +
    theme_minimal() +
    theme(legend.position = "bottom")
}

# Create Plots
insertion_plot <- plot_comparison(
  experimental_results$insertion, 
  theoretical_results$insertion,
  "AVL Tree Insertion: Experimental vs Theoretical Comparisons",
  "Number of Comparisons"
)

search_plot <- plot_comparison(
  experimental_results$search, 
  theoretical_results$search,
  "AVL Tree Search: Experimental vs Theoretical Comparisons",
  "Number of Comparisons"
)

# Arrange and Display Plots
grid.arrange(insertion_plot, search_plot, ncol = 2)

# Print Detailed Results
print("Insertion Experimental Results:")
print(experimental_results$insertion)
print("\nSearch Experimental Results:")
print(experimental_results$search)

# Ensure necessary libraries are loaded
library(ggplot2)

# Experimental results (ensure these are data frames or matrices with the necessary columns)
# Example assuming experimental_results$insertion and experimental_results$search are data frames

# Insertion plot
insertion_plot <- ggplot(experimental_results$insertion, aes(x = size)) +
  geom_point(aes(y = min_comparisons, color = "Min Comparisons"), size = 3) + 
  geom_point(aes(y = max_comparisons, color = "Max Comparisons"), size = 3) + 
  geom_point(aes(y = mean_comparisons, color = "Mean Comparisons"), size = 3) + 
  labs(title = "AVL Tree Insertion: Experimental Results", 
       x = "Tree Size", 
       y = "Number of Comparisons") +
  theme_minimal() +
  theme(legend.position = "bottom")

# Search plot
search_plot <- ggplot(experimental_results$search, aes(x = size)) +
  geom_point(aes(y = min_comparisons, color = "Min Comparisons"), size = 3) + 
  geom_point(aes(y = max_comparisons, color = "Max Comparisons"), size = 3) + 
  geom_point(aes(y = mean_comparisons, color = "Mean Comparisons"), size = 3) + 
  labs(title = "AVL Tree Search: Experimental Results", 
       x = "Tree Size", 
       y = "Number of Comparisons") +
  theme_minimal() +
  theme(legend.position = "bottom")

# Display both plots side by side
library(gridExtra)
grid.arrange(insertion_plot, search_plot, ncol = 2)

#print(insertion_plot)
#print(search_plot)