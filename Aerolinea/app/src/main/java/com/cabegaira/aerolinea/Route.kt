package com.cabegaira.aerolinea


class Route {
    
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