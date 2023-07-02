import React, { useEffect, useState } from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

export default function ScheduleTable({ shouldUpdate }) {
    const [data, setData] = useState([]);

    useEffect(() => {
        fetchData();
    }, []);             //Questo viene eseguito al momento del montaggio del componente

    useEffect(() => {
        if (shouldUpdate) {
            fetchData();
        }
    }, [shouldUpdate]);    //Questo viene eseguito per aggiornare il componente quando viene aggiunto un elemento.

    const fetchData = () => {
        fetch('http://localhost:8080/schedule/getAllSchedules', { method: "GET" })
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
                setData(data);
            })
            .catch((error) => {
                console.log('Errore durante il recupero dei dati: ', error);
            });
    }

    const formatDateTime = (datetimeString) => {
        const options = {
            year: '2-digit',
            month: '2-digit',
            day: '2-digit',
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit',
        };

        if (datetimeString) {
            var date = new Date(datetimeString);
            return date.toLocaleDateString('it-IT', options);
        }
        return "";
    };

    return (
        <TableContainer component={Paper}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>ID</TableCell>
                        <TableCell>Creation</TableCell>
                        <TableCell>Type of Request</TableCell>
                        <TableCell>Date 1</TableCell>
                        <TableCell>Date 2</TableCell>
                        <TableCell>Timespan</TableCell>
                        <TableCell>Multiplier</TableCell>
                        <TableCell>Forex Ticker</TableCell>
                        <TableCell>Cron String</TableCell>
                        <TableCell>State</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {data.map((item) => (
                        <TableRow key={item.id} >
                            <TableCell>{item.id}</TableCell>
                            <TableCell>{formatDateTime(item.creation)}</TableCell>
                            <TableCell>{item.typeOfRequest}</TableCell>
                            <TableCell>{formatDateTime(item.date1)}</TableCell>
                            <TableCell>{formatDateTime(item.date2)}</TableCell>
                            <TableCell>{item.timespan}</TableCell>
                            <TableCell>{item.multiplier}</TableCell>
                            <TableCell>{item.forexTicker}</TableCell>
                            <TableCell>{item.cronString}</TableCell>
                            <TableCell>{item.state}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};
