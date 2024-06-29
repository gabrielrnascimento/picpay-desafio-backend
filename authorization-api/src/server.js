const express = require("express");
const app = express();
app.use(express.json());

const PORT = 8082;

app.get("/health-check", (req, res) => {
    console.log("Request received at /health-check route")
    res.send({
        message: "Authorization service is running",
    });
});

app.get("/authorize", (req, res) => {
    console.log("Request received at /authorize route")
    const options = ["Authorized", "Unauthorized"];
    const randomIndex = Math.floor(Math.random() * options.length);
    const randomOption = options[randomIndex];
    console.log(`Authorization result: ${randomOption}`)
    res.send({
        message: randomOption,
    });
});

app.listen(PORT, () => {
    console.log(`Server is running on port ${PORT}`);
});
