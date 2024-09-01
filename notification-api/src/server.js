const express = require("express");
const app = express();
app.use(express.json());

const PORT = 8083;

app.get("/health-check", (req, res) => {
    console.log("Request received at /health-check route")
    res.send({
        message: "Notification service is running",
    });
});

app.get("/notify", (req, res) => {
    console.log("Request received at /notify route")
    const options = ["Online", "Offline"];
    const randomIndex = Math.floor(Math.random() * options.length);
    const randomOption = options[randomIndex];
    console.log(`Notification API status: ${randomOption}`)
    if (randomOption === "Online") {
        res.send({
            message: "Notification sent successfully",
        });
    } else {
        res.status(500).send({
            message: "Failed to send notification",
        });
    }
});

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
