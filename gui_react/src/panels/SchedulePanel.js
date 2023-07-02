import React, { useState } from 'react';
import ScheduleTable from '../tables/ScheduleTable';
import ScheduleForm from '../tables/ScheduleForm';

export default function SchedulePanel() {

    const [isTableUpdated, setTableUpdated] = useState(false);

    function handleTableUpdate () {
        setTableUpdated(true);
      };

    return (
        <>
            <ScheduleTable shouldUpdate={isTableUpdated} />
            <ScheduleForm onFormSubmit={handleTableUpdate} />
        </>
    );
}