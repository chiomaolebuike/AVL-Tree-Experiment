/**
 * Statement class to store knowledge base entries
 * 18/03/2025
 * OLBCHI002
 */

public class Statement implements Comparable<Statement> {
   private String term;
   private String sentence;
   private double confidence;
   
   /**
    * Constructor for the Statement class
    * @param term The term or keyword
    * @param sentence The associated sentence or definition
    * @param confidence The confidence score (0.0 to 1.0)
    */
   public Statement(String term, String sentence, double confidence) {
       this.term = term;
       this.sentence = sentence;
       this.confidence = confidence;
   }
   
   /**
    * Get the term of this statement
    * @return The term as a String
    */
   public String getTerm() {
       return term;
   }
   
   /**
    * Set the term of this statement
    * @param term The new term value
    */
   public void setTerm(String term) {
       this.term = term;
   }
   
   /**
    * Get the sentence of this statement
    * @return The sentence as a String
    */
   public String getSentence() {
       return sentence;
   }
   
   /**
    * Set the sentence of this statement
    * @param sentence The new sentence value
    */
   public void setSentence(String sentence) {
       this.sentence = sentence;
   }
   
   /**
    * Get the confidence score of this statement
    * @return The confidence as a double
    */
   public double getConfidence() {
       return confidence;
   }
   
   /**
    * Set the confidence score of this statement
    * @param confidence The new confidence value
    */
   public void setConfidence(double confidence) {
       this.confidence = confidence;
   }
   
   /**
    * Compare this Statement to another Statement based on their terms
    * @param other The other Statement to compare with
    * @return negative if this term comes before, 0 if equal, positive if this term comes after
    */
   @Override
   public int compareTo(Statement other) {
       return this.term.compareTo(other.term);
   }
   

}