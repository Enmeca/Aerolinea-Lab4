package com.cabegaira.aerolinea.CRUD.Rutas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.content.Intent

import com.cabegaira.aerolinea.logic.Route
import com.cabegaira.aerolinea.data.Routes

import com.cabegaira.aerolinea.R

import android.app.DatePickerDialog.OnDateSetListener
import android.app.DatePickerDialog
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.google.android.material.navigation.NavigationView
import java.text.SimpleDateFormat
import java.util.*

import android.widget.AutoCompleteTextView
import android.widget.EditText

import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.DatePicker
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import java.util.*

class CreateRoute : AppCompatActivity(){

    /*var rutas: Routes = Routes.instance

    private lateinit var etOrigin: AutoCompleteTextView
    private lateinit var etDestiny: AutoCompleteTextView

    private var etName: EditText? = null
    private var etDuration: EditText? = null
    private var etDate: EditText? = null

    private var etfoto: ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.create_route_activity)

        etOrigin = findViewById<AutoCompleteTextView>(R.id.spinnerSalida)
        etDestiny = findViewById<AutoCompleteTextView>(R.id.spinnerLlegado)

        etName = findViewById<View>(R.id.txtName) as EditText
        etDuration = findViewById<View>(R.id.txtDuracion) as EditText

        etDate = findViewById<View>(R.id.dataPicker) as EditText
        etDate!!.setOnClickListener { showDateDialog(etDate) }

        val countries = arrayOf(
            "Afghanistan",
            "Åland Islands",
            "Albania",
            "Algeria",
            "American Samoa",
            "AndorrA",
            "Angola",
            "Anguilla",
            "Antarctica",
            "Antigua and Barbuda",
            "Argentina",
            "Armenia",
            "Aruba",
            "Australia",
            "Austria",
            "Azerbaijan",
            "Bahamas",
            "Bahrain",
            "Bangladesh",
            "Barbados",
            "Belarus",
            "Belgium",
            "Belize",
            "Benin",
            "Bermuda",
            "Bhutan",
            "Bolivia",
            "Bosnia and Herzegovina",
            "Botswana",
            "Bouvet Island",
            "Brazil",
            "British Indian Ocean Territory",
            "Brunei Darussalam",
            "Bulgaria",
            "Burkina Faso",
            "Burundi",
            "Cambodia",
            "Cameroon",
            "Canada",
            "Cape Verde",
            "Cayman Islands",
            "Central African Republic",
            "Chad",
            "Chile",
            "China",
            "Christmas Island",
            "Cocos (Keeling) Islands",
            "Colombia",
            "Comoros",
            "Congo",
            "Congo, The Democratic Republic of the",
            "Cook Islands",
            "Costa Rica",
            "Cote D\"Ivoire",
            "Croatia",
            "Cuba",
            "Cyprus",
            "Czech Republic",
            "Denmark",
            "Djibouti",
            "Dominica",
            "Dominican Republic",
            "Ecuador",
            "Egypt",
            "El Salvador",
            "Equatorial Guinea",
            "Eritrea",
            "Estonia",
            "Ethiopia",
            "Falkland Islands (Malvinas)",
            "Faroe Islands",
            "Fiji",
            "Finland",
            "France",
            "French Guiana",
            "French Polynesia",
            "French Southern Territories",
            "Gabon",
            "Gambia",
            "Georgia",
            "Germany",
            "Ghana",
            "Gibraltar",
            "Greece",
            "Greenland",
            "Grenada",
            "Guadeloupe",
            "Guam",
            "Guatemala",
            "Guernsey",
            "Guinea",
            "Guinea-Bissau",
            "Guyana",
            "Haiti",
            "Heard Island and Mcdonald Islands",
            "Holy See (Vatican City State)",
            "Honduras",
            "Hong Kong",
            "Hungary",
            "Iceland",
            "India",
            "Indonesia",
            "Iran, Islamic Republic Of",
            "Iraq",
            "Ireland",
            "Isle of Man",
            "Israel",
            "Italy",
            "Jamaica",
            "Japan",
            "Jersey",
            "Jordan",
            "Kazakhstan",
            "Kenya",
            "Kiribati",
            "Korea, Democratic People\"S Republic of",
            "Korea, Republic of",
            "Kuwait",
            "Kyrgyzstan",
            "Lao People\"S Democratic Republic",
            "Latvia",
            "Lebanon",
            "Lesotho",
            "Liberia",
            "Libyan Arab Jamahiriya",
            "Liechtenstein",
            "Lithuania",
            "Luxembourg",
            "Macao",
            "Macedonia, The Former Yugoslav Republic of",
            "Madagascar",
            "Malawi",
            "Malaysia",
            "Maldives",
            "Mali",
            "Malta",
            "Marshall Islands",
            "Martinique",
            "Mauritania",
            "Mauritius",
            "Mayotte",
            "Mexico",
            "Micronesia, Federated States of",
            "Moldova, Republic of",
            "Monaco",
            "Mongolia",
            "Montserrat",
            "Morocco",
            "Mozambique",
            "Myanmar",
            "Namibia",
            "Nauru",
            "Nepal",
            "Netherlands",
            "Netherlands Antilles",
            "New Caledonia",
            "New Zealand",
            "Nicaragua",
            "Niger",
            "Nigeria",
            "Niue",
            "Norfolk Island",
            "Northern Mariana Islands",
            "Norway",
            "Oman",
            "Pakistan",
            "Palau",
            "Palestinian Territory, Occupied",
            "Panama",
            "Papua New Guinea",
            "Paraguay",
            "Peru",
            "Philippines",
            "Pitcairn",
            "Poland",
            "Portugal",
            "Puerto Rico",
            "Qatar",
            "Reunion",
            "Romania",
            "Russian Federation",
            "RWANDA",
            "Saint Helena",
            "Saint Kitts and Nevis",
            "Saint Lucia",
            "Saint Pierre and Miquelon",
            "Saint Vincent and the Grenadines",
            "Samoa",
            "San Marino",
            "Sao Tome and Principe",
            "Saudi Arabia",
            "Senegal",
            "Serbia and Montenegro",
            "Seychelles",
            "Sierra Leone",
            "Singapore",
            "Slovakia",
            "Slovenia",
            "Solomon Islands",
            "Somalia",
            "South Africa",
            "South Georgia and the South Sandwich Islands",
            "Spain",
            "Sri Lanka",
            "Sudan",
            "Suriname",
            "Svalbard and Jan Mayen",
            "Swaziland",
            "Sweden",
            "Switzerland",
            "Syrian Arab Republic",
            "Taiwan, Province of China",
            "Tajikistan",
            "Tanzania, United Republic of",
            "Thailand",
            "Timor-Leste",
            "Togo",
            "Tokelau",
            "Tonga",
            "Trinidad and Tobago",
            "Tunisia",
            "Turkey",
            "Turkmenistan",
            "Turks and Caicos Islands",
            "Tuvalu",
            "Uganda",
            "Ukraine",
            "United Arab Emirates",
            "United Kingdom",
            "United States",
            "United States Minor Outlying Islands",
            "Uruguay",
            "Uzbekistan",
            "Vanuatu",
            "Venezuela",
            "Viet Nam",
            "Virgin Islands, British",
            "Virgin Islands, U.S.",
            "Wallis and Futuna",
            "Western Sahara",
            "Yemen",
            "Zambia",
            "Zimbabwe"
        )

        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        etDestiny.setAdapter(adapter)
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, countries)
        etOrigin.setAdapter(adapter)
    }

    fun send(view: View?) {

        val name = etName!!.text.toString()
        val origin = etOrigin!!.text.toString()
        val destiny = etDestiny!!.text.toString()
        val exitDate = etDate!!.text.toString()
        val duration = etDuration!!.text.toString()
        val ruta = Route(
            name,
            origin,
            destiny,
            exitDate,
            duration,
            R.drawable.banner_main
        )
        rutas.addRoute(ruta)

        Toast.makeText(this, "Añadido Exitosamente", Toast.LENGTH_SHORT).show()
    }


    // --------------------------DatePicker---------------------
    private fun showDateDialog(date_in: EditText?) {
        val calendar = Calendar.getInstance()
        val dateSetListener = OnDateSetListener { view, year, month, dayOfMonth ->
            calendar[Calendar.YEAR] = year
            calendar[Calendar.MONTH] = month
            calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            date_in!!.setText(simpleDateFormat.format(calendar.time))
        }
        DatePickerDialog(
            this@CreateRoute,
            dateSetListener,
            calendar[Calendar.YEAR],
            calendar[Calendar.MONTH],
            calendar[Calendar.DAY_OF_MONTH]
        ).show()
    }*/
}