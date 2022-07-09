### Email Service using Java Mail API

A basic email service for Java using Java Mail API for ease of usage.

#### Contents
**EmailMessage.java**
Composed email to send.

**EmailService.java**
An interface for EmailService.

**EmailServiceImpl.java**
EmailService implementation.

**Main.java**
contains sample usages.

#### Sample Usage
    public class Main 
    {
        public static void main( String[] args )
        {
        	Properties props = new Properties();
        	
        	try {
    			props.load(new FileInputStream("src/main/resources/mail.properties"));
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
        	
        	var emailService = new EmailServiceImpl(props);
			var message = new EmailMessage();
        	
        	try {
        		
    			message.addRecipient(RecipientTypes.TO, "email@gmail.com");
    			message.setSubject("Sample Email Subject");
    			message.appendBody("<b>Sample Body using HTML</b>");
    			message.appendBody("Plain Text body appended");
    			message.attachFile(new File("E:\\folder\\file.txt"));
    			emailService.send(message);
    		} catch (MessagingException | IOException e) {
    			e.printStackTrace();
    		}
            
        }
    }


### Setup

#### Creating Configuration Properties
Before using the EmailService class first we need to create a **Properties** object from file or programmatically


#### Creating Properties from file
Create **Properties** object then use **load** method and pass a **File** object where the properties file is located.

         Properties props = new Properties();
            try {
                props.load(new FileInputStream("src/main/resources/mail.properties"));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
			

#### Properties file example
##### mail.properties

    mail.smtp.host=smtp.gmail.com
    mail.smtp.port=587
    mail.smtp.auth=true
    mail.smtp.starttls.enable=true
    username=email@gmail.com
    password=putyourpasswordhere
    mail.debug=false


#### Creating Properties without using a properties file 

**put** method signature
**put(Object key, Object value)**

        	Properties props = new Properties();
        	
        	props.put("mail.smtp.host", "smtp.gmail.com");
        	props.put("mail.smtp.port", "587");


#### Creating EmailService object
We need to create a **EmailService** object then pass the **Properties** as argument

    EmailService emailService = new EmailServiceImpl(props);

### Composing Email

#### Setting up Email Recipients
Use the following methods to set the recipients

**Recipient Types**
RecipientTypes.TO,
RecipientTypes.CC,
RecipientTypes.BCC,

**addRecipient(Recipient Type, String email)**
Add a single recipient according to Recipient Type.

**addRecipients(String[] emails)**
Add array of recipients according to Recipient Type.


#### Setting Email Subject
**setSubject(String subject)**
Sets the email subject 

#### Setting up Email Body

**appendBody(String body)**
Append string to message body. Use HTML tags for text formatting.

**setBody(String body)**
Replaces current body with a new one.

**attachFile(File file)**
Attach file to email.

#### Sending Email
**send(EmailMessage message)**
After composing the Email using the methods above, you can call this method to begin sending an email.
