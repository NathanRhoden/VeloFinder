import "../messagebox/MessageBox.css"
interface Message {
    text: string;
}

export default function MessageBox(message: Message) {
    return (
        <div className="text">
            {message.text}
        </div>
    )
}