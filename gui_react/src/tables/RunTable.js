import React, { useEffect, useState } from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

export default function ScheduleTable() {
    const [data, setData] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8080/run/getAllRuns', { method: "GET" })
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
                setData(data);
            })
            .catch((error) => {
                console.log('Errore durante il recupero dei dati: ', error);
            });
    }, []);

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
                        <TableCell>Started</TableCell>
                        <TableCell>Finished</TableCell>
                        <TableCell>Status</TableCell>
                        <TableCell>Schedule ID</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {data.map((item) => (
                        <TableRow key={item.id} >
                            <TableCell>{item.id}</TableCell>
                            <TableCell>{formatDateTime(item.started)}</TableCell>
                            <TableCell>{formatDateTime(item.finished)}</TableCell>
                            <TableCell>{item.status}</TableCell>
                            <TableCell>{item.schedule}</TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};
