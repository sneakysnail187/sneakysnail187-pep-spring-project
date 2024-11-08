package com.example.entity;

public class Edit {
   
    public String messageText;
    /**
     * A default, no-args constructor, as well as correctly formatted getters and setters, are needed for
     * Jackson Objectmapper to work.
     */
    public Edit(){

    }
    /**
     * When posting a new Account, the id can be generated by the database. In that case, a constructor without
     * messageText is needed.
     * @param messageText
     */
    public Edit(String messageText){
        this.messageText = messageText;
    }
    
    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     * @return messageText
     */
    public String getmessageText() {
        return messageText;
    }
    /**
     * Properly named getters and setters are necessary for Jackson ObjectMapper to work. You may use them as well.
     * @param messageText
     */
    public void setmessageText(String messageText) {
        this.messageText = messageText;
    }
    /**
     * Overriding the default equals() method adds functionality to tell when two objects are identical, allowing
     * Assert.assertEquals and List.contains to function.
     * @param o the other object.
     * @return true if o is equal to this object.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edit account = (Edit) o;
        return messageText == account.messageText;
    }
    /**
     * Overriding the default toString() method allows for easy debugging.
     * @return a String representation of this class.
     */
    @Override
    public String toString() {
        return "Edit{" +
                "messageText=" + messageText +
                '}';
    }
}
