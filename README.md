🧠 Artificial Intelligence Chatbot (Java)

🎯 Project Overview

A Java-based AI chatbot capable of interactive communication using Natural Language Processing (NLP) and machine-learning-inspired logic.
It identifies the user’s intent from text input using TF–IDF and cosine similarity and responds to the most relevant FAQ from its knowledge base.
It also includes rule-based fallbacks and a simple Swing GUI for real-time interaction.

⚙️ Features

✅ Pure Java implementation (no external libraries)
✅ NLP techniques: tokenization, stopword removal, TF–IDF, cosine similarity
✅ Trained on FAQ data from knowledge_base.txt
✅ Rule-based fallback for greetings and unknown inputs
✅ Interactive GUI built using Java Swing

📂 Project Structure
File	Description
ChatUI.java	GUI and main entry point
ChatbotCore.java	Core logic: NLP matching and rule-based responses
KnowledgeBase.java	Loads questions and answers from file
NLP.java	Tokenization, TF-IDF, and cosine similarity
knowledge_base.txt	Training data (FAQs)
Task 3.mp4	Demo video showing chatbot in action
🧩 How to Run

Open Terminal / CMD
Navigate to the src/ folder.

cd src


Compile all files

javac *.java


Run the chatbot

java ChatUI


Make sure knowledge_base.txt is present in the same working directory as the compiled .class files.

💬 Sample Interaction
Bot: Hi — I'm a Java chatbot. Ask me FAQ-style questions or say 'help'.
You: Hello
Bot: Hello! How can I help you today?

You: I forgot my password
Bot: Click "Forgot password" on the login page, enter your registered email, and follow the reset link sent to your email. (confidence: 0.69)

You: Thanks
Bot: You're welcome! Anything else I can help with?

🧠 Knowledge Base Example

(From knowledge_base.txt)

🧩 Technologies Used

Java SE 8+

Java Swing GUI

Core NLP (TF–IDF, Cosine Similarity)

Rule-based logic

🚀 Future Enhancements

Integration with a real database (MySQL / MongoDB)

Admin panel to add/edit FAQs

Use of pretrained embeddings for semantic understanding

Web-based interface using JSP/Servlets or Spring Boot (optional)

👨‍💻 Author

Venkata Sudheer
B.Tech (Computer Science) — Siddhartha Institute of Engineering and Technology
📍 Andhra Pradesh, India
💻 Interest: Java Full Stack Development
