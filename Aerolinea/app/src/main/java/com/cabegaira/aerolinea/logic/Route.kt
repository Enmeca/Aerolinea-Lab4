package com.cabegaira.aerolinea.logic

import java.io.Serializable


class Route : Serializable {
    
    var Name:String = " "

    var Origin:String = " "
    var Destiny:String = " "

    var ExitDate:String = " "
    var Duration:String = " "

    var Foto:Int = 0

    internal constructor(Name: String, Origin: String, Destiny: String, ExitDate: String, Duration: String, Foto:Int) {
        this.Name = Name
        this.Origin = Origin
        this.Destiny = Destiny
        this.ExitDate = ExitDate
        this.Duration = Duration
        this.Foto = Foto
    }

}