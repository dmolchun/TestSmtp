# TestSmtp
Telegram bot with the ability to subscribe / unsubscribe to notifications using the commands
1. / register email@email.com
2. / deregister email@email.com

SMTP Server receives incoming mail. Emails sent via this server to e-mail from the list of registered should be send to the telegram chat rooms, which are registered to receive notifications by this e-mail. Telegram messages contains information about the subject, from, to and body of a e-mail.

H2 database is used for data storage

# Telegram bot
The application.properties file contains two variables.\
This variables can be taken from telegram bot BotFather
1. telegram.botname - Telegram bot name
2. telegram.token - Telegram bot token

This bot has two commands
1. /register <e-mail> - Add current chatId to list of chats, which is notified when smtp server receives message addressed this e-mail
2. /deregister <email> - Remove current chatId from list (par.1)
  
E-mails and chatIds has many-to-many connection
# SMTP server
The application.properties file contains three variables.
1. smtpserver.enabled - Enabling SMTP server
2. smtpserver.hostName - SMTP server host
3. smtpserver.port - SMTP server port
