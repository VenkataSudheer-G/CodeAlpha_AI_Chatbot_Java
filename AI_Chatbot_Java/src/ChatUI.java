import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ChatUI {
    private final JFrame frame = new JFrame("Java FAQ Chatbot");
    private final JTextArea chatArea = new JTextArea();
    private final JTextField inputField = new JTextField();
    private final ChatbotCore bot;

    public ChatUI(ChatbotCore bot) {
        this.bot = bot;
        init();
    }

    private void init() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());

        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(chatArea);

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(inputField, BorderLayout.CENTER);
        JButton send = new JButton("Send");
        inputPanel.add(send, BorderLayout.EAST);

        frame.add(scroll, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.SOUTH);

        inputField.addActionListener(e -> sendMessage());
        send.addActionListener(e -> sendMessage());

        appendBot("Hi — I'm a Java chatbot. Ask me FAQ-style questions or say 'help'.");
        frame.setVisible(true);
    }

    private void appendUser(String t) {
        chatArea.append("You: " + t + "\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    private void appendBot(String t) {
        chatArea.append("Bot: " + t + "\n\n");
        chatArea.setCaretPosition(chatArea.getDocument().getLength());
    }

    private void sendMessage() {
        String text = inputField.getText().trim();
        if (text.isEmpty()) return;
        appendUser(text);
        inputField.setText("");
        String reply = bot.respond(text);
        appendBot(reply);
    }

    public static void main(String[] args) {
        try {
            KnowledgeBase kb = new KnowledgeBase();
            kb.loadFromFile("knowledge_base.txt");

            ChatbotCore core = new ChatbotCore(kb);
            core.train();

            SwingUtilities.invokeLater(() -> new ChatUI(core));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Failed to load knowledge base: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
}
