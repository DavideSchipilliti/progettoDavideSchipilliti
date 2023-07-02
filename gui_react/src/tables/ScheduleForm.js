import '../index.css';
import { TextField, Button, Box } from "@mui/material";

export default function ScheduleForm({ onFormSubmit }) {

    const handleClick = () => {
        const requestData = {};

        const typeOfRequest = document.getElementById("typeOfRequest").value;
        if (typeOfRequest) {
            requestData.typeOfRequest = typeOfRequest;
        }

        const date1 = document.getElementById("date1").value;
        if (date1) {
            requestData.date1 = convertStringToTimestamp(date1);
        }

        const date2 = document.getElementById("date2").value;
        if (date2) {
            requestData.date2 = convertStringToTimestamp(date2);
        }

        const timespan = document.getElementById("timespan").value;
        if (timespan) {
            requestData.timespan = timespan;
        }

        const multiplier = document.getElementById("multiplier").value;
        if (multiplier) {
            requestData.multiplier = multiplier;
        }

        const forexTicker = document.getElementById("forexTicker").value;
        if (forexTicker) {
            requestData.forexTicker = forexTicker;
        }

        const cronString = document.getElementById("cronString").value;
        if (cronString) {
            requestData.cronString = cronString;
        }

        const state = document.getElementById("state").value;
        if (state) {
            requestData.state = state;
        }

        if (Object.keys(requestData).length === 0) {
            // Nessun campo compilato, non eseguire la chiamata REST
            console.log("Compila almeno un campo prima di salvare.");
            onFormSubmit();
            return;
        }

        fetch("http://localhost:8080/schedule/setSchedule", {
            method: "PUT", headers: { "Content-Type": "application/json" },
            body: JSON.stringify(requestData)
        })
            .then((response) => response.json())
            .then((data) => { console.log("Dati salvati:", data); })
            .catch((error) => {
                console.error("Errore durante il salvataggio:", error);
            });

        onFormSubmit();
    };

    function convertStringToTimestamp(dateTimeString) {
        const [datePart, timePart] = dateTimeString.split(' ');
        const [year, month, day] = datePart.split('-');
        const [hour, minute, second] = timePart.split(':');

        const timestamp = new Date(year, month - 1, day, hour, minute, second);
        return timestamp;
    }


    return (
        <Box
            component="form"
            sx={{'& > :not(style)': { m: 1, width: '25ch' },}}
            noValidate
            autoComplete="off">
            <p></p>
            <h3 class="ScheduleFormText">Aggiungi una nuova schedulazione:</h3>
            <p></p>
            <TextField
                id='typeOfRequest'
                style={{ width: "200px", margin: "5px" }}
                type="text"
                label="Type of Request"
                variant="outlined"
            />
            <TextField
                id='date1'
                style={{ width: "200px", margin: "5px" }}
                type="text"
                label="Date 1"
                variant="outlined"
                helperText="aaaa-mm-gg hh:mm:ss"
            />
            <TextField
                id='date2'
                style={{ width: "200px", margin: "5px" }}
                type="text"
                label="Date 2"
                variant="outlined"
                helperText="aaaa-mm-gg hh:mm:ss"
            />
            <TextField
                id='timespan'
                style={{ width: "200px", margin: "5px" }}
                type="text"
                label="Timespan"
                variant="outlined"
            />
            <TextField
                id='multiplier'
                style={{ width: "200px", margin: "5px" }}
                type="text"
                label="Multiplier"
                variant="outlined"
            />
            <TextField
                id='forexTicker'
                style={{ width: "200px", margin: "5px" }}
                type="text"
                label="Forex Ticker"
                variant="outlined"
            />
            <TextField
                id='cronString'
                style={{ width: "200px", margin: "5px" }}
                type="text"
                label="Cron String"
                variant="outlined"
            />
            <TextField
                id='state'
                style={{ width: "200px", margin: "5px" }}
                type="text"
                label="State"
                variant="outlined"
            />
            <Button variant="contained" color="primary" onClick={handleClick}>
                Save
            </Button>
        </Box>
    );
}