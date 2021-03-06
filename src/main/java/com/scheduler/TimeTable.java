package com.scheduler;

public class TimeTable {
    public static Slot[] slot;

    public TimeTable() {
        int k = 0;
        int subjectno = 0;
        int hourcount = 1;
        int days = InputData.daysperweek;
        int hours = InputData.hoursperday;
        int countOfStudentsGroup = InputData.nostudentgroup;

        // creating as many slots as the no of blocks in overall timetable
        slot = new Slot[hours * days * countOfStudentsGroup];

        // looping for every student group
        for (int i = 0; i < countOfStudentsGroup; i++) {

            subjectno = 0;
            // for every slot in a week for a student group
            for (int j = 0; j < hours * days; j++) {

                StudentGroup sg = InputData.studentgroup[i];

                // if all subjects have been assigned required hours we give
                // free periods
                if (subjectno >= sg.nosubject) {
                    slot[k++] = null;
                }

                // if not we create new slots
                else {

                    slot[k++] = new Slot(sg, sg.teacherid[subjectno], sg.subject[subjectno]);

                    // suppose java has to be taught for 5 hours then we make 5
                    // slots for java, we keep track through hourcount
                    if (hourcount < sg.hours[subjectno]) {
                        hourcount++;
                    } else {
                        hourcount = 1;
                        subjectno++;
                    }

                }

            } // end inner for

        } // end outer for

    }// end constructor

    public static Slot[] returnSlots() {
        return slot;
    }

}
