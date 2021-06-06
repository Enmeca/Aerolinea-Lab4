package com.cabegaira.aerolinea.data

import com.cabegaira.aerolinea.R
import com.cabegaira.aerolinea.logic.Schedule

class Schedules {
    private var Schedules : ArrayList<Schedule> = ArrayList<Schedule>()

    init{
        addSchedule(
            Schedule(
                1,
                "24/02 3:550",
                "4:48"
            )
        )
    }

    private object HOLDER {
        val INSTANCE = Schedules()
    }

    companion object {
        val instance: Schedules by lazy{
            HOLDER.INSTANCE
        }
    }

    fun addSchedule(app: Schedule){
        Schedules?.add(app)
    }

    fun getSchedules(): ArrayList<Schedule>{
        return this.Schedules!!
    }
    fun deleteSchedules(position: Int){
        Schedules!!.removeAt(position)
    }

    fun editSchedule(position: Int, Schedule: Schedule){
        Schedules!![position] = Schedule

    }
    fun getSchedule(position: Int) : Schedule {
        return this.Schedules!![position]
    }
}