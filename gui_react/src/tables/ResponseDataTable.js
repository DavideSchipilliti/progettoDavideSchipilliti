import React, { useEffect, useState } from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';

export default function ResponseDataTable() {
    const [data, setData] = useState([]);

    useEffect(() => {
        fetch('http://localhost:8181/response/getAllResponses', { method: "GET" })
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
                        <TableCell>RequestID</TableCell>
                        <TableCell>Type Of Response</TableCell>
                        <TableCell>Time</TableCell>
                        <TableCell>Ticker</TableCell>
                        <TableCell>Open Price</TableCell>
                        <TableCell>Close Price</TableCell>
                        <TableCell>Highest Price</TableCell>
                        <TableCell>Lowest Price</TableCell>
                        <TableCell>Trading Volume</TableCell>
                        <TableCell>VWAP</TableCell>
                        <TableCell>Number of Transaction</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {data.map((item) =>
                        item.results.map((result) => (
                        <TableRow key={'${item.requestId}-${result.ticker}'}>
                            <TableCell>{item.requestId}</TableCell>
                            <TableCell>{item.typeOfResponse}</TableCell>
                            <TableCell>{formatDateTime(result.startTimestamp)}</TableCell>
                            <TableCell>{result.ticker}</TableCell>
                            <TableCell>{result.openPrice}</TableCell>
                            <TableCell>{result.closePrice}</TableCell>
                            <TableCell>{result.highestPrice}</TableCell>
                            <TableCell>{result.lowestPrice}</TableCell>
                            <TableCell>{result.tradingVolume}</TableCell>
                            <TableCell>{result.vwap}</TableCell>
                            <TableCell>{result.numberOfTransaction}</TableCell>
                        </TableRow>
                            ))
                        )}
                </TableBody>
            </Table>
        </TableContainer>
    );
};