/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmoordb.core.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import org.apache.commons.beanutils.BeanUtils;

/**
 *
 * @author avbravo
 */
public class JmoordbCoreUtil {

    private static String opertativeSystem = System.getProperty("os.name").toLowerCase();
    public static Exception exception;

    private static final Logger LOG = Logger.getLogger(JmoordbCoreUtil.class.getName());
    private static final String EMAIL_REGEX = "^[\\w-\\+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$";

    // static Pattern object, since pattern is fixed
    private static Pattern pattern;

    // non-static Matcher object because it's created from the input String
    private static Matcher matcher;

    // <editor-fold defaultstate="collapsed" desc="String letterToUpper(String texto)">
    public static String letterToUpper(String texto) {
        try {
            return Character.toUpperCase(texto.charAt(0)) + texto.substring(1);

        } catch (Exception ex) {
            ////Test.msg("letterToUpper() " + ex.getLocalizedMessage());
        }
        return texto;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String fileSeparator()">
    //https://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html
    public static String fileSeparator() {
        return System.getProperty("file.separator");

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String letterToLower(String texto)">
    /**
     * ConvertirLetraMinuscula
     *
     * @param s_cadena
     * @param caracter
     * @return
     */
    public static String letterToLower(String texto) {

        try {
            return Character.toLowerCase(texto.charAt(0)) + texto.substring(1);

        } catch (Exception ex) {
            ////Test.msg("letterToLower() " + ex.getLocalizedMessage());
        }
        return texto;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String traductor(String texto, String idioma)">
    public static String traductor(String texto, String idioma) {
        String traduccion = "";
        try {

        } catch (Exception e) {
            ////Test.msg("traductor() "+e.getLocalizedMessage());
            new JmoordbCoreException("traductor() " + e.getLocalizedMessage());
        }
        return traduccion;
    }
    // </editor-fold>

    public static Date getFechaHoraActual() {
        LocalDateTime ahora = LocalDateTime.now();
        Date date2 = Date.from(ahora.atZone(ZoneId.systemDefault()).toInstant());
        return date2;
    }

    // <editor-fold defaultstate="collapsed" desc="nombreEntity(String texto)">
    /**
     * obtiene el texto despues del ultimo puento
     *
     * @param texto (com.avbravo.entity.Rol)
     * @return Rol
     */
    public static String nombreEntity(String texto) {
        String result = "";
        // TODO code application logic here
        try {

            Integer pos = texto.lastIndexOf(".");

            result = texto.substring(pos + 1, texto.length());

        } catch (Exception e) {
        }
        return result;

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="errorMessage"> 
    public static void errorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            errorMessage(msg);
        } else {
            errorMessage(defaultMsg);
        }
    }    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="errorMessages"> 

    public static void errorMessages(List<String> messages) {
        for (String message : messages) {
            errorMessage(message);

        }
    }    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="errorMessage(String msg)"> 

    public static void errorMessage(String msg) {
        System.out.println(msg);
//        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                msg, msg);
//        FacesContext.getCurrentInstance().addMessage(null, facesMsg);

    }    // </editor-fold>

//    // <editor-fold defaultstate="collapsed" desc="testMessage"> 
//    public static void testMessage(String msg) {
//        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                msg, msg);
//        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
//
//    }    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="successMessage"> 
    public static void successMessage(String msg) {
        System.out.println(msg);
//        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg,
//                msg);
//        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="warningMessage"> 

    public static void warningMessage(String msg) {
        System.out.println(msg);
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, msg, ""));

    }    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="fatalMessage"> 

    public static void fatalMessage(String msg) {
        System.out.println(msg);
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, msg, ""));

    }    // </editor-fold>

//     // <editor-fold defaultstate="collapsed" desc="infoDialog"> 
//    public static void infoDialog(String titulo, String texto) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo,
//                texto);
//        RequestContext.getCurrentInstance().showMessageInDialog(message);
//    }
//    // </editor-fold>
//
//    // <editor-fold defaultstate="collapsed" desc="warningDialog"> 
//    public static void warningDialog(String titulo, String texto) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, titulo,
//                texto);
//        RequestContext.getCurrentInstance().showMessageInDialog(message);
//        LOG.warning(titulo + " " + texto);
//    }    // </editor-fold>
//// <editor-fold defaultstate="collapsed" desc="fatalDialog"> 
//
//    public static void fatalDialog(String titulo, String texto) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_FATAL, titulo,
//                texto);
//        RequestContext.getCurrentInstance().showMessageInDialog(message);
//        LOG.warning(titulo + " " + texto);
//    }    // </editor-fold>
//// <editor-fold defaultstate="collapsed" desc="errorDialog"> 
//
//    public static void errorDialog(String titulo, String texto) {
//        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
//                titulo, texto);
//        RequestContext.getCurrentInstance().showMessageInDialog(message);
//        LOG.warning(titulo + " " + texto);
//    }    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="isVacio(String texto)()"> 
    /**
     * return true si es null empty equals("")
     *
     * @param texto
     * @return
     */
    public static Boolean isVacio(String texto) {
        texto = texto.trim();
        return texto == null || texto.equals("") || texto.isEmpty();
    }// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="isVacio(Integer texto)"> 

    /**
     * return true si es null empty equals("")
     *
     * @param texto
     * @return
     */
    public static Boolean isVacio(Integer texto) {
        return texto == null || texto.equals("");
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="isVacio(Integer texto)"> 
    /**
     * return true si es null empty equals("")
     *
     * @param texto
     * @return
     */
    public static Boolean isVacio(Double texto) {
        return texto == null || texto.equals("");
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean isNegativo(Double texto)"> 
    /**
     * return true si es null empty equals("")
     *
     * @param texto
     * @return
     */
    public static Boolean isNegativo(Double numero) {
        return numero == null || numero.equals("") || numero < 0;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean isNegativo(Double texto)"> 
    /**
     * return true si es null empty equals("")
     *
     * @param texto
     * @return
     */
    public static Boolean isNegativo(Integer numero) {
        return numero == null || numero.equals("") || numero < 0;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getUUID"> 
    /**
     * genera id
     *
     * @return returna un randomUUID automatico
     */
    public static String getUUID() {

        UUID uuid = UUID.randomUUID();
        return uuid.toString();

    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="totalCaracteresVaciosAlfinalCadena() ">
    /**
     * Cuenta la cantidad de espacios al final de una cadena
     *
     * @param texto
     * @return
     */
    public static Integer totalEspaciosAlfinalCadena(String texto) {
        Integer count = 0;
        for (int x = texto.length() - 1; x > 0; x--) {
            System.out.println("Inverso " + x + ": " + texto.charAt(x));
            char c = texto.charAt(x);

            if (texto.charAt(x) == ' ') {
                count++;
            }
        }
        return count;

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="fechaActual"> 
    public static java.util.Date fechaActual() {
        LocalDateTime timePoint = LocalDateTime.now();
        LocalDate currentDate = LocalDate.now();
        java.util.Date date = java.sql.Date.valueOf(currentDate);
        return date;
    }    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="generateUniqueID">
    /**
     * Genera una serie de caracteres aleatorios
     *
     * @return
     */
    public static String generateUniqueID() {
        String strValue = "";
        UUID idUnique = UUID.randomUUID();
        strValue = idUnique.toString();
        return strValue.toUpperCase();
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="getFechaActual"> 
    public static java.util.Date getFechaActual() {
        LocalDateTime timePoint = LocalDateTime.now();
        LocalDate currentDate = LocalDate.now();
        java.util.Date date = java.sql.Date.valueOf(currentDate);
        return date;
    }    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="isBisiesto(Integer anio)">
    public static Boolean isBisiesto(Integer anio) {
        try {
            GregorianCalendar calendar = new GregorianCalendar();

            if (calendar.isLeapYear(anio)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            errorMessage("isBisiesto() " + e.getLocalizedMessage());
        }
        return true;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="numberDayOfMonth(Integer anio, Integer mes)"> 
// devuelve el total de dias del mes
    public static Integer numberDayOfMonth(Integer anio, Integer mes) {
        Integer dias = 0;
        try {
            switch (mes) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    dias = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    dias = 30;
                    break;
                case 2:
                    if (isBisiesto(anio)) {
                        dias = 29;
                    } else {
                        dias = 28;
                    }
//                    if ((anio % 4 == 0 && dias % 100 != 0) || anio % 400 == 0) {
//                        dias = 29;
//                    } else {
//                        dias = 28;
//                    }
                    break;
                default:
                    System.out.println("\nEl mes " + mes + " es incorrecto.");
                    break;
            }

        } catch (Exception e) {
            errorMessage("numberDayOfMonth() " + e.getLocalizedMessage());
        }
        return dias;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="totalDiaDelMes(Integer anio, Integer mes)"> 
// devuelve el total de dias del mes

    public static Integer totalDiaDelMes(Integer anio, Integer mes) {
        Integer dias = 0;
        try {
            switch (mes) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    dias = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    dias = 30;
                    break;
                case 2:
                    if (isBisiesto(anio)) {
                        dias = 29;
                    } else {
                        dias = 28;
                    }
//                    if ((anio % 4 == 0 && dias % 100 != 0) || anio % 400 == 0) {
//                        dias = 29;
//                    } else {
//                        dias = 28;
//                    }
                    break;
                default:
                    System.out.println("\nEl mes " + mes + " es incorrecto.");
                    break;
            }

        } catch (Exception e) {
            errorMessage("numberDayOfMonth() " + e.getLocalizedMessage());
        }
        return dias;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="getAnioActual"> 

    public static Integer getAnioActual() {
        java.util.Calendar ca = java.util.Calendar.getInstance();
        java.sql.Date mydate = new java.sql.Date(ca.getTimeInMillis());
        return ca.get(Calendar.YEAR);
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="anioActual"> 

    public static Integer anioActual() {
        java.util.Calendar ca = java.util.Calendar.getInstance();
        java.sql.Date mydate = new java.sql.Date(ca.getTimeInMillis());
        return ca.get(Calendar.YEAR);
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="mesActual"> 
    public static Integer mesActual() {
        java.util.Calendar ca = java.util.Calendar.getInstance();
        java.sql.Date mydate = new java.sql.Date(ca.getTimeInMillis());
        return ca.get(Calendar.MONTH) + 1;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="fechaHoraActual()"> 
    public static Date fechaHoraActual() {
        LocalDateTime ahora = LocalDateTime.now();
        Date date2 = Date.from(ahora.atZone(ZoneId.systemDefault()).toInstant());
        return date2;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="dateFormatToString"> 
    /**
     * formatea una fecha a "dd/MM/yyyy hh:mm a"
     *
     * @param fecha
     * @param format
     * @return
     */
    public static String dateFormatToString(Date fecha, String... format) {
        String dateformat = "";
        String f = "dd/MM/yyyy hh:mm a";
        try {
            if (format.length != 0) {
                f = format[0];

            }
            SimpleDateFormat sdf = new SimpleDateFormat(f);
            dateformat = sdf.format(fecha);
        } catch (Exception e) {
        }
        return dateformat;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="hourFromDateToString(Date fecha)"> 
    /**
     * Devuelve una hora en formato hh:mm a o se puede especificar el formato
     * deseado
     *
     * @param fecha
     * @param format
     * @return
     */
    public static String hourFromDateToString(Date fecha, String... format) {
        String h = "";
        try {
            String f = "hh:mm a";
            if (format.length != 0) {
                f = format[0];

            }

            h = dateFormatToString(fecha, f);
        } catch (Exception e) {
            errorMessage("hourFromDateToString() " + e.getLocalizedMessage());
        }
        return h;

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="dateBetween(Date fechaToSearch, Date fechainicio, Date fechafin)"> 
    /**
     * busca una fecha si esta entre fechas
     *
     * @param fechaToSearch
     * @param fechainicio
     * @param fechafin
     * @return
     */
    public static Boolean dateBetween(Date fechaToSearch, Date fechainicio, Date fechafin) {
        try {
//            Date fechainiciot = converterDate(fechainicio);
//                    Date fechafint = converterDate(fechafin);
            if (fechaToSearch.equals(fechainicio) || fechaToSearch.equals(fechafin) || (fechaToSearch.after(fechainicio) && fechaToSearch.before(fechafin))) {
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="getNombreMes"> 
    public static String getNombreMes(Integer numeromes) {
        try {
            String nombre = "";
            List<String> listMeses = new ArrayList<>();
            listMeses.add("Enero");
            listMeses.add("Febrero");
            listMeses.add("Marzo");
            listMeses.add("Abril");
            listMeses.add("Mayo");
            listMeses.add("Junio");
            listMeses.add("Julio");
            listMeses.add("Agosto");
            listMeses.add("Septiembre");
            listMeses.add("Octubre");
            listMeses.add("Noviembre");
            listMeses.add("Diciembre");
            return listMeses.get(numeromes);

        } catch (Exception e) {
        }
        return "";
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="nombreMes(Integer numeromes) "> 

    public static String nombreMes(Integer numeromes) {
        try {
            String nombre = "";
            List<String> listMeses = new ArrayList<>();
            listMeses.add("Enero");
            listMeses.add("Febrero");
            listMeses.add("Marzo");
            listMeses.add("Abril");
            listMeses.add("Mayo");
            listMeses.add("Junio");
            listMeses.add("Julio");
            listMeses.add("Agosto");
            listMeses.add("Septiembre");
            listMeses.add("Octubre");
            listMeses.add("Noviembre");
            listMeses.add("Diciembre");
            return listMeses.get(numeromes);

        } catch (Exception e) {
        }
        return "";
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="mesToMonth"> 
    /**
     * Convierte un nombre de mes a un objeto Month Month month =
     * mesToMonth("Febrero); Devuelve un month.FEBRARY;
     *
     * @param mes
     * @return
     */
    public static Month mesToMonth(String mes) {
        mes = mes.toLowerCase();
        Month month = Month.JANUARY;
        try {
            switch (mes) {
                case "enero":
                    month = Month.JANUARY;
                    break;
                case "febrero":
                    month = Month.FEBRUARY;
                    break;
                case "marzo":
                    month = Month.MARCH;
                    break;
                case "abril":
                    month = Month.APRIL;
                    break;
                case "mayo":
                    month = Month.MAY;
                    break;
                case "junio":
                    month = Month.JUNE;
                    break;
                case "julio":
                    month = Month.JULY;
                    break;
                case "agosto":
                    month = Month.AUGUST;
                    break;
                case "septiembre":
                    month = Month.SEPTEMBER;
                    break;
                case "octubre":
                    month = Month.OCTOBER;
                    break;
                case "noviembre":
                    month = Month.NOVEMBER;
                    break;
                case "diciembre":
                    month = Month.DECEMBER;
                    break;

            }

        } catch (Exception e) {
            errorMessage("mesToMonth() " + e.getLocalizedMessage());
        }
        return month;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="mesDeUnaFecha"> 
    public static Integer mesDeUnaFecha(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int anio = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        return mes;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="mesDeUnaFechaStartEneroWith1(Date date)"> 
    public static Integer mesDeUnaFechaStartEneroWith1(Date date) {
        int mes = 0;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int anio = calendar.get(Calendar.YEAR);
            mes = calendar.get(Calendar.MONTH) + 1;
            int dia = calendar.get(Calendar.DAY_OF_MONTH);
            return mes;
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return 0;

    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="mesDeUnaFechaStartEneroWith0(Date date)"> 

    public static Integer mesDeUnaFechaStartEneroWith0(Date date) {
        int mes = 0;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);

            int anio = calendar.get(Calendar.YEAR);
            mes = calendar.get(Calendar.MONTH);
            int dia = calendar.get(Calendar.DAY_OF_MONTH);
            return mes;
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return 0;

    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="anioDeUnaFecha"> 
    public static Integer anioDeUnaFecha(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int anio = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        return anio;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="diaDeUnaFecha"> 
    public static Integer diaDeUnaFecha(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int anio = calendar.get(Calendar.YEAR);
        int mes = calendar.get(Calendar.MONTH) + 1;
        int dia = calendar.get(Calendar.DAY_OF_MONTH);
        return dia;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="horaDeUnaFecha(Date date)"> 
    public static Integer horaDeUnaFecha(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        return hora;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="horaMinutoAMPMDeUnaFecha(Date date)(Date date)"> 
    public static String horaMinutoAMPMDeUnaFecha(Date date) {

        int hora = horaDeUnaFecha(date);
        int minutos = minutosDeUnaFecha(date);
        String time12h = "AM";
        if (hora > 12) {
            hora = hora - 12;
            time12h = "PM";
        }
        return hora + ":" + minutos + time12h;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="horaDeUnaFechaFormato12(Date date)"> 

    public static Integer horaDeUnaFechaFormato12H(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int hora = calendar.get(Calendar.HOUR_OF_DAY);
        if (hora > 12) {
            hora = hora - 12;
        }
        return hora;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="minutosDeUnaFecha"> 
    public static Integer minutosDeUnaFecha(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int minuto = calendar.get(Calendar.MINUTE);
        return minuto;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="diaActual"> 
    public static Integer diaActual() {
        java.util.Calendar ca = java.util.Calendar.getInstance();
        java.sql.Date mydate = new java.sql.Date(ca.getTimeInMillis());
        return ca.get(Calendar.DATE);
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="nameOfDay()"> 
    public static String nameOfDay(LocalDate date) {
        String nombre = "DOMINGO";
        try {
            DayOfWeek dia = date.getDayOfWeek();
            dia.name();
            switch (dia.name()) {
                case "SATURDAY":
                    nombre = "SABADO";
                    break;
                case "SUNDAY":
                    nombre = "DOMINGO";
                    break;
                case "MONDAY":
                    nombre = "LUNES";
                    break;
                case "TUESDAY":
                    nombre = "MARTES";
                    break;
                case "WEDNESDAY":
                    nombre = "MIERCOLES";
                    break;
                case "THURSDAY":
                    nombre = "JUEVES";
                    break;
                case "FRIDAY":
                    nombre = "VIERNES";
                    break;

            }

        } catch (Exception e) {
            errorMessage("nameOfDay() " + e.getLocalizedMessage());
        }
        return nombre;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="nameOfDay(Date date)"> 

    public static String nameOfDay(Date date) {
        String nombre = "";
        try {
            LocalDate localDate = date.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            nombre = nameOfDay(localDate);
        } catch (Exception e) {
            errorMessage("nameOfDay() " + e.getLocalizedMessage());
        }
        return nombre;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="firstLeterOfDay"> 
    /**
     * devuelve la primera letra del dia
     *
     * @param date
     * @return
     */
    public static String firstLetterOfDay(LocalDate date) {
        String letra = "";
        try {
            letra = nameOfDay(date);
            if (letra.length() > 1) {
                letra = letra.substring(0, 1);
            }

        } catch (Exception e) {
            errorMessage("firsLetterOfDay() " + e.getLocalizedMessage());
        }
        return letra;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="nameDayOfMonth"> 
    /**
     * devuelve un List<String> correspondiente a las letras de cada dia del mes
     *
     * @param year
     * @param mes
     * @return
     */
    public static List<String> nameDayOfMonth(Integer year, String mes) {
        List<String> names = new ArrayList<>();
        try {
            LocalDate date;

            Month month = mesToMonth(mes);

            for (int i = 1; i <= month.maxLength(); i++) {

                date = LocalDate.of(year, month, i);
                String name = nameOfDay(date);
                names.add(name);

            }
        } catch (Exception e) {
            errorMessage("nameDayOfMonth() " + e.getLocalizedMessage());
        }
        return names;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="primeraFechaAnio"> 
    /**
     * devuelve la primera fecha del año
     *
     * @return
     */
    public static Date primeraFechaAnio() {
        LocalDate now = LocalDate.now();//# 2015-11-23
        Integer year = now.getYear();
        Integer month = 1;
        Integer day = 1;
        LocalDate firstDay = LocalDate.of(year, month, day);

        Date date = java.sql.Date.valueOf(firstDay);
        return date;

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="ultimaFechaAnio"> 
    /**
     * devuelve la ultima fecha del año
     *
     * @return
     */
    public static Date ultimaFechaAnio() {
        LocalDate now = LocalDate.now();//# 2015-11-23
        Integer year = now.getYear();
        Integer month = 12;
        Integer day = 31;
        LocalDate firstDay = LocalDate.of(year, month, day);

        Date date = java.sql.Date.valueOf(firstDay);
        return date;

    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="dateFirtsOfMonth"> 
    /**
     *
     * @param month
     * @return devuelve una fecha correspondiente al primer dia de ese mes
     */
    public static Date dateFirtsOfMonth(Integer year, Integer month) {
        LocalDate now = LocalDate.now();//# 2015-11-23
        Integer day = 1;
        LocalDate firstDay = LocalDate.of(year, month, day);
        Date date = java.sql.Date.valueOf(firstDay);
        return date;
    }
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="Date primerDiaDelMesEnFecha(Integer year, Integer month)"> 

    /**
     *
     * @param month
     * @return devuelve una fecha correspondiente al primer dia de ese mes
     */
    public static Date primerDiaDelMesEnFecha(Integer year, Integer month) {
        LocalDate now = LocalDate.now();//# 2015-11-23
        Integer day = 1;
        LocalDate firstDay = LocalDate.of(year, month, day);
        Date date = java.sql.Date.valueOf(firstDay);
        return date;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="integerToDate"> 
    public static Date integerToDate(Integer year, Integer month, Integer day) {

        LocalDate firstDay = LocalDate.of(year, month, day);
        Date date = java.sql.Date.valueOf(firstDay);
        return date;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="dateLastOfMonth(Integer year, Integer month)"> 
    public static Date dateLastOfMonth(Integer year, Integer month) {
        LocalDate now = LocalDate.now();//# 2015-11-23
        Integer day = numberDayOfMonth(year, month);

        LocalDate firstDay = LocalDate.of(year, month, day);
        Date date = java.sql.Date.valueOf(firstDay);
        return date;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Date ultimoDiaDelMesEnFecha(Integer year, Integer month)"> 

    public static Date ultimoDiaDelMesEnFecha(Integer year, Integer month) {
        LocalDate now = LocalDate.now();//# 2015-11-23
        Integer day = numberDayOfMonth(year, month);

        LocalDate firstDay = LocalDate.of(year, month, day);
        Date date = java.sql.Date.valueOf(firstDay);
        return date;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Date ultimoDiaDelMesEnFecha(Integer year, Integer month)"> 

    public static Date fechaConHora0(Date date) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, 0);
        return calendar.getTime();
//        Calendar ahoraCal = Calendar.getInstance();
//System.out.println(ahoraCal.getClass());
//ahoraCal.set(2004,1,7);
//System.out.println(ahoraCal.getTime());
//ahoraCal.set(2004,1,7,7,0,0);
//System.out.println(ahoraCal.getTime());
//        LocalDate now = LocalDate.now();//# 2015-11-23
//        Integer day = numberDayOfMonth(year, month);
//
//        LocalDate firstDay = LocalDate.of(year, month, day);
//        Date date2 = java.sql.Date.valueOf(firstDay);
//        return date2;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="iSODate"> 
    public static String iSODate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat.format(date);
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="tiempo"> 
    public static LocalTime tiempo() {
        LocalTime now = LocalTime.now();

        return now;

    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="printTiempo"> 
    public static String printTiempo() {
        LocalTime now = LocalTime.now();
        String tiempo = "";

        tiempo = "En este momento son las %d horas con %d minutos y %d segundos\n" + now.getHour()
                + now.getMinute() + now.getSecond();

        return tiempo;

    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="hour"> 
    public static LocalTime hour() {

        return LocalTime.now();
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="diasEntreFechas"> 

    public static Integer diasEntreFechas(Date fechaMayor, Date fechaMenor) {
        int d = 0;
        try {
            long diferenciaEn_ms = fechaMayor.getTime() - fechaMenor.getTime();

            long dias = diferenciaEn_ms / (1000 * 60 * 60 * 24);
            d = (int) dias;
        } catch (Exception e) {
            System.out.println("diasEntreFechas() " + e.getLocalizedMessage());
        }

        return d;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="fechaMenor(Date date1, Date date2)">
    public static Boolean fechaMenor(Date date1, Date date2) {
        Boolean esmenor = false;
        try {
            if (date1.compareTo(date2) < 0) {
                esmenor = true;
            }
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return esmenor;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="fechaMayor(Date date1, Date date2)">
    public static Boolean fechaMayor(Date date1, Date date2) {
        Boolean esmenor = false;
        try {
            if (date1.compareTo(date2) > 0) {
                esmenor = true;
            }
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return esmenor;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="fechaIgual(Date date1, Date date2)">

    public static Boolean fechaIgual(Date date1, Date date2) {
        Boolean esmenor = false;
        try {
            if (date1.compareTo(date2) == 0) {
                esmenor = true;
            }
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return esmenor;
    }
    // </editor-fold>

// <editor-fold defaultstate="collapsed" desc="sumarMesaFechaActual"> 
    /*
    
     */
    public static Date sumarMesaFechaActual(Integer mes) {
        java.util.Date date = new Date();
        try {
            LocalDate localDate = LocalDate.now().plusMonths(mes);
            date = java.sql.Date.valueOf(localDate);

        } catch (Exception e) {
            System.out.println("diasEntreFechas() " + e.getLocalizedMessage());
        }

        return date;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="sumarMesaFecha(date,mes)"> 
    /**
     * suma a la fecha el numero de mes
     *
     * @param date
     * @param mes
     */
    public static Date sumarMesaFecha(Date date, Integer mes) {
        java.util.Date dateresult = new Date();
        try {

            ZoneId defaultZoneId = ZoneId.systemDefault();
            Instant instant = date.toInstant();
            LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();

            localDate = localDate.plusMonths(mes);
            dateresult = java.sql.Date.valueOf(localDate);

        } catch (Exception e) {
            errorMessage("sumarMesaFecha() " + e.getLocalizedMessage());
        }
        return dateresult;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="segundosToHoraString"> 
    public static String segundosToHoraString(Integer segundos) {
        String resultado = "";
        try {
            int hours = segundos / 3600;
            int minutes = (segundos % 3600) / 60;
            segundos = segundos % 60;
            resultado = twoDigitString(hours) + " : " + twoDigitString(minutes) + " : " + twoDigitString(segundos);
        } catch (Exception e) {
            errorMessage("segundosToHoraString() " + e.getLocalizedMessage());
        }
        return resultado;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="twoDigitString"> 
    private static String twoDigitString(int number) {

        if (number == 0) {
            return "00";
        }

        if (number / 10 == 0) {
            return "0" + number;
        }

        return String.valueOf(number);
    }// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="milisegundos"> 

    public static long milisegundos() {
        long milisegundos = 0;
        try {
            milisegundos = System.nanoTime();

        } catch (Exception e) {
            System.out.println("getMilisegundos() " + e.getLocalizedMessage());
        }
        return milisegundos;
    }// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="milisegundosTranscurridos"> 
    public static long milisegundosTranscurridos(long t0, long t1) {
        long milisegundos = 0;
        try {
            milisegundos = TimeUnit.NANOSECONDS.toMillis(t1 - t0);

        } catch (Exception e) {
            errorMessage("getMilisegundos() " + e.getLocalizedMessage());
            System.out.println("getMilisegundos() " + e.getLocalizedMessage());
        }
        return milisegundos;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="milisegundosToSegundos"> 
    public static Integer milisegundosToSegundos(long milisegundos) {
        Integer seconds = 0;
        try {
            seconds = (int) (milisegundos / 1000) % 60;
        } catch (Exception e) {
            errorMessage("miliseguntosToSegundos() " + e.getLocalizedMessage());
        }
        return seconds;
    }
// </editor-fold>

// <editor-fold defaultstate="collapsed" desc="milisegundosToMinutos"> 
    public static Integer milisegundosToMinutos(long milisegundos) {
        Integer minutes = 0;
        try {
            minutes = (int) ((milisegundos / (1000 * 60)) % 60);
        } catch (Exception e) {
            errorMessage("miliseguntosToMinutos() " + e.getLocalizedMessage());
        }
        return minutes;

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="milisegundosToHoras"> 
    public static Integer milisegundosToHoras(long milisegundos) {
        Integer hours = 0;
        try {
            hours = (int) ((milisegundos / (1000 * 60 * 60)) % 24);
        } catch (Exception e) {
            errorMessage("miliseguntosToMinutos() " + e.getLocalizedMessage());
        }
        return hours;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="milisegundosToTiempoString"> 
    /**
     * devuelve el tiempo de los milisegundos en el formato hh:mm:ss
     * milisegundos 1222 devuelve; 1:2:23
     *
     * @param milisegundos
     * @return
     */
    public static String milisegundosToTiempoString(long milisegundos) {
        String tiempoString = "";

        try {

            tiempoString = milisegundosToHoras(milisegundos) + " : "
                    + milisegundosToMinutos(milisegundos) + " : " + milisegundosToSegundos(milisegundos);

        } catch (Exception e) {
            errorMessage("milisegundosToTiempoString() " + e.getLocalizedMessage());
        }
        return tiempoString;

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="mesAnterior(String mes) "> 
    /**
     * devuelve el nombre del mes anterior
     *
     * @param mes
     * @return
     */
    public static String mesAnterior(String mes) {
        String mesanterior = "";
        try {
            switch (mes.toLowerCase()) {
                case "enero":
                    mesanterior = "diciembre";
                    break;

                case "febrero":
                    mesanterior = "enero";
                    break;
                case "marzo":
                    mesanterior = "febrero";
                    break;
                case "abril":
                    mesanterior = "marzo";
                    break;
                case "mayo":
                    mesanterior = "abril";
                    break;
                case "junio":
                    mesanterior = "mayo";
                    break;
                case "julio":
                    mesanterior = "junio";
                    break;
                case "agosto":
                    mesanterior = "julio";
                    break;
                case "septiembre":
                    mesanterior = "agosto";
                    break;
                case "octubre":
                    mesanterior = "septiembre";
                    break;
                case "noviembre":
                    mesanterior = "octubre";
                    break;
                case "diciembre":
                    mesanterior = "noviembre";
                    break;
            }
        } catch (Exception e) {
            errorMessage("mesAnterior() " + e.getLocalizedMessage());
        }
        return mesanterior;
    }    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="fechaActualEnMilisegundos()"> 
    public static Long fechaActualEnMilisegundos() {
        return ZonedDateTime.now().toInstant().toEpochMilli();
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="letterDayOfMonth(Integer year, String mes)"> 
    /**
     * devuelve un List<String> correspondiente a las letras de cada dia del mes
     *
     * @param year
     * @param mes
     * @return
     */
    public static List<String> letterDayOfMonth(Integer year, String mes) {
        List<String> letters = new ArrayList<>();
        try {
            LocalDate date;

            Month month = mesToMonth(mes);

            for (int i = 1; i <= month.maxLength(); i++) {

                date = LocalDate.of(year, month, i);
                String letra = firstLetterOfDay(date);
                letters.add(letra);

            }
        } catch (Exception e) {
            errorMessage("letterDayOfMonth() " + e.getLocalizedMessage());
        }
        return letters;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="numeroMes(String mes)">

    /**
     * Devuelve el numero de mes iniciando enero= 0 hasta diciembre= 11
     *
     * @param mes
     * @return
     */
    public static Integer numeroMes(String mes) {
        List<String> listMeses = Arrays.asList("enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre");
        Integer i = -1;
        for (String l : listMeses) {
            i++;
            if (l.toLowerCase().equals(mes.toLowerCase())) {
                return i;
            }
        }

        return -1;

    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="numeroMesStartEneroWith1(String mes)">

    /**
     * devuelve el numero de mes iniciando enero en 1 y diciembre en 12
     *
     * @param mes
     * @return
     */
    public static Integer numeroMesStartEneroWith1(String mes) {
        List<String> listMeses = Arrays.asList("enero", "febrero", "marzo", "abril", "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre");
        Integer i = -1;
        for (String l : listMeses) {
            i++;
            if (l.toLowerCase().equals(mes.toLowerCase())) {
                return i + 1;
            }
        }

        return -1;

    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean isValidCierreMensual(Integer anioselected, String messelected, Integer diaminimo,ResourceBundle rs)">
    /**
     *
     * @param mes
     * @return
     */
    public static Boolean isValidCierreMensual(Integer anioselected, String messelected, Integer diaminimo, ResourceBundle rs) {
        try {

            if (anioselected <= 0) {
                warningMessage(rs.getString("warning.anionegativo"));
                return false;
            }
            if (anioselected > getAnioActual()) {
                warningMessage(rs.getString("warning.anomayorqueactual"));
                return false;
            }

            Integer anio = getAnioActual() - anioselected;
            if (anio.intValue() > 1) {
                warningMessage(rs.getString("warning.aniomuyantiguo"));
                return false;
            }
            if (anio.intValue() == 1 && !messelected.toLowerCase().equals("diciembre")) {
                warningMessage(rs.getString("warning.debecerrardiciembredelañoanterior"));
                return false;
            }
            Integer diaactual = diaActual();
            Integer mesactual = mesActual();
            //Esto pasarlo a avbravoutils
            Integer numeromesseleccionado = numeroMes(messelected);

            if (numeromesseleccionado > mesactual) {
                warningMessage(rs.getString("warning.mesacerrarmayoractual"));
                return false;
            }
            if (numeromesseleccionado.equals(mesactual) && diaactual < diaminimo) {
                warningMessage(rs.getString("warning.estacerrandoelmesmuypronto"));
                return false;
            }
            return true;
        } catch (Exception e) {
            warningMessage("isValidCierreMensual" + e.getLocalizedMessage());
        }
        return false;
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="String isValidCierreMensual(Integer anioselected, String messelected, Integer diaminimo)">
    /**
     *
     * @param mes
     * @return
     */
    public static String isValidCierreMensual(Integer anioselected, String messelected, Integer diaminimo) {
        try {

            if (anioselected <= 0) {
                return "warning.anionegativo";

            }
            if (anioselected > getAnioActual()) {
                return "warning.anomayorqueactual";

            }

            Integer anio = getAnioActual() - anioselected;
            if (anio.intValue() > 1) {
                return "warning.aniomuyantiguo";
            }
            if (anio.intValue() == 1 && !messelected.toLowerCase().equals("diciembre")) {
                return "warning.debecerrardiciembredelañoanterior";
            }
            Integer diaactual = diaActual();
            Integer mesactual = mesActual();
            //Esto pasarlo a avbravoutils
            Integer numeromesseleccionado = numeroMes(messelected);

            if (numeromesseleccionado > mesactual) {
                return "warning.mesacerrarmayoractual";

            }
            if (numeromesseleccionado.equals(mesactual) && diaactual < diaminimo) {
                return "warning.estacerrandoelmesmuypronto";

            }
            return "";
        } catch (Exception e) {
            warningMessage("isValidCierreMensual" + e.getLocalizedMessage());
        }
        return "";
    }

// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Date setHourToDate(Date date,Integer hour)">
    /**
     * asigna la hora a la fecha que se le pase Hora minima: 0 Hora maxima: 23
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date setHourToDate(Date date, Integer hour, Integer minutes) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, hour);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="LocalDate convertirJavaDateToLocalDate(Date dateToConvert)">
    public static LocalDate convertirJavaDateToLocalDate(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="LocalDate convertirJavaDateToLocalDate(Date dateToConvert)">
    public static Date convertirLocalDateToJavaDate(LocalDate localDate) {
        return java.sql.Date.valueOf(localDate);
    }    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String showDate(Date date)">
    public static String showDate(Date date) {
        String h = "";
        try {
            h = dateFormatToString(date, "dd/MM/yyyy");
        } catch (Exception e) {
            errorMessage("showDate() " + e.getLocalizedMessage());
        }
        return h;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String showHour(Date date)">
    public static String showHour(Date date) {
        String h = "";
        try {
            h = hourFromDateToString(date);
        } catch (Exception e) {
            errorMessage("showHour() " + e.getLocalizedMessage());
        }
        return h;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="java.util.Date  localDateToDate(LocalDate localDate)"> 
    public static java.util.Date localDateToDate(LocalDate localDate) {
        java.util.Date date = java.sql.Date.valueOf(localDate);
        return date;
    } // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="java.util.Date insertHorasMinutosSegundosToDate(Date date, Integer hora,Integer minutos, Integer segundos) "> 
    /**
     * Inserta horas, minutos y segundos a una fecha
     *
     * @param date
     * @param hora
     * @param minutos
     * @param segundos
     * @return
     */
    public static java.util.Date insertHoursMinutesSecondsToDate(Date date, Integer hora, Integer minutos, Integer segundos) {
        Integer anio = anioDeUnaFecha(date);
        Integer mes = mesDeUnaFecha(date);
        Integer dia = diaDeUnaFecha(date);
        LocalDateTime start = LocalDateTime.of(anio, mes, dia, hora, minutos, segundos);
        Date ldate = Date.from(start.atZone(ZoneId.systemDefault()).toInstant());
        return ldate;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Date primerDiaDelMesActual()"> 
    /**
     *
     * @param month
     * @return Date primerDiaDelActual()
     */
    public static Date primerDiaDelMesActual() {
        LocalDate now = LocalDate.now();//# 2015-11-23
        Integer day = 1;
        LocalDate firstDay = LocalDate.of(anioActual(), mesActual(), day);
        Date date = java.sql.Date.valueOf(firstDay);
        return date;
    }
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" Date primerDiaDelMesActualConPrimeraHoraDelDia()"> 

    /**
     *
     * @param month
     * @return Date primerDiaDelActual()
     */
    public static Date primerDiaDelMesActualConPrimeraHoraDelDia() {
        LocalDate now = LocalDate.now();//# 2015-11-23
        Integer day = 1;
        LocalDate firstDay = LocalDate.of(anioActual(), mesActual(), day);
        Date date = java.sql.Date.valueOf(firstDay);
        date = insertHoursMinutesSecondsToDate(date, 0, 0, 0);
        return date;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Date ultimoDiaDelMesActual(Integer year, Integer month) "> 
    public static Date ultimoDiaDelMesActual() {
        LocalDate now = LocalDate.now();//# 2015-11-23
        Integer day = numberDayOfMonth(anioActual(), mesActual());

        LocalDate firstDay = LocalDate.of(anioActual(), mesActual(), day);
        Date date = java.sql.Date.valueOf(firstDay);
        return date;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Date ultimoDiaDelMesActualConHoraFinal() "> 

    public static Date ultimoDiaDelMesActualConHoraFinal() {
        LocalDate now = LocalDate.now();//# 2015-11-23
        Integer day = numberDayOfMonth(anioActual(), mesActual());

        LocalDate firstDay = LocalDate.of(anioActual(), mesActual(), day);
        Date date = java.sql.Date.valueOf(firstDay);
        date = insertHoursMinutesSecondsToDate(date, 23, 59, 0);
        return date;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="nameOfClass()">
    public static String nameOfClass() {
        final StackTraceElement e = Thread.currentThread().getStackTrace()[2];
        final String s = e.getClassName();
        return s.substring(s.lastIndexOf('.') + 1, s.length());
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="nameOfClassAndMethod())">
    public static String nameOfClassAndMethod() {
        final StackTraceElement e = Thread.currentThread().getStackTrace()[2];
        final String s = e.getClassName();
        return s.substring(s.lastIndexOf('.') + 1, s.length()) + "." + e.getMethodName();
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="nameOfMethod())">
    public static String nameOfMethod() {
        final StackTraceElement e = Thread.currentThread().getStackTrace()[2];
        final String s = e.getClassName();
        return e.getMethodName();
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Object copyBeans(Object destino, Object fuente)">
    /**
     * Copia el contenido de un bean en otro
     *
     * @param destino
     * @param fuente
     * @return
     */
    public static Object copyBeans(Object destino, Object fuente) {
        try {
            BeanUtils.copyProperties(destino, fuente);
        } catch (Exception e) {
        }

        return destino;
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean insertarTextoArchivo(String rutaArchivo, String search, String textoInsertar, boolean antes)>

    /*
     * Inserta texto en el archivo antes o despues de la linea donde se
     * encuentre la cadena search el parametro antes = true : indica que se
     * insertara antes antes = false : indica que se insertara despues
     * InsertarTextoArchivo("/home/avbravo/Documentos/etiquetas.properties",
     * "name", "email=\"@ww\"", false)
     */
    public static Boolean insertarTextoArchivo(String rutaArchivo, String search, String textoInsertar, Boolean antes) {
        try {

            File file = new File(rutaArchivo);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line = "", oldtext = "";
            boolean encontrado = false;
            while ((line = reader.readLine()) != null) {
                if (line.indexOf(search) != -1) {
                    if (antes) {
                        //insertarlo antes
                        oldtext += textoInsertar + "\r\n" + line + "\r\n";
                    } else {
                        //insertar despues
                        oldtext += line + "\r\n" + textoInsertar + "\r\n";
                    }

                    encontrado = true;

                } else {
                    oldtext += line + "\r\n";
                }

            }
            reader.close();

            if (encontrado) {
                FileWriter writer = new FileWriter(rutaArchivo);
                writer.write(oldtext);
                writer.close();

                return true;
            }

        } catch (Exception ex) {
            System.out.println("insertarTextoArchivo()" + ex.getLocalizedMessage());
            // errorDialog("insertarTextoArchivo()", ex.getLocalizedMessage());
        }
        return false;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean existFile(String filePath) ">
    public static Boolean existFile(String filePath) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return false;
                // file.createNewFile();
            }
            return true;
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return false;

    }
    // </editor-fold>   

    // <editor-fold defaultstate="collapsed" desc="String extensionOfFileInPath(String filenamePath)">
    /**
     *
     * @param filenamePath
     * @return devuelve la extension de un archivo en un path
     */
    public static String extensionOfFileInPath(String filenamePath) {
        String extension = "";
        try {
            extension = filenamePath.substring(filenamePath.lastIndexOf('.') + 1, filenamePath.length());
        } catch (Exception e) {
        }
        return extension;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) ">
    /**
     * fuente https://www.baeldung.com/java-compress-and-uncompress
     *
     * @param fileToZip
     * @param fileName
     * @param zipOut
     * @throws IOException
     */
    private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) {
        try {
            if (fileToZip.isHidden()) {
                return;
            }
            if (fileToZip.isDirectory()) {
                if (fileName.endsWith("/")) {
                    zipOut.putNextEntry(new ZipEntry(fileName));
                    zipOut.closeEntry();
                } else {
                    zipOut.putNextEntry(new ZipEntry(fileName + "/"));
                    zipOut.closeEntry();
                }
                File[] children = fileToZip.listFiles();
                for (File childFile : children) {
                    zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
                }
                return;
            }
            FileInputStream fis = new FileInputStream(fileToZip);
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);
            byte[] bytes = new byte[1024];
            int length;
            while ((length = fis.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
            fis.close();
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="unzipFileToDirectory(String fileZipWithPath, String pathToUnzip)">
    /**
     * Fuente
     * https://examples.javacodegeeks.com/core-java/util/zip/extract-zip-file-with-subdirectories/
     *
     * @param fileZipWithPath
     * @param pathToUnzip
     * @return
     */
    public static Boolean unzipFileToDirectory(String fileZipWithPath, String pathToUnzip) {
        try {

            String filename = fileZipWithPath;

            File srcFile = new File(filename);

            // create a directory with the same name to which the contents will be extracted
            String zipPath = filename.substring(0, filename.length() - 4);
            File temp = new File(zipPath);
            temp.mkdir();

            ZipFile zipFile = null;

            try {

                zipFile = new ZipFile(srcFile);

                // get an enumeration of the ZIP file entries
                Enumeration<? extends ZipEntry> e = zipFile.entries();

                while (e.hasMoreElements()) {

                    ZipEntry entry = e.nextElement();

                    File destinationPath = new File(zipPath, entry.getName());

                    //create parent directories
                    destinationPath.getParentFile().mkdirs();

                    // if the entry is a file extract it
                    if (entry.isDirectory()) {
                        continue;
                    } else {

                        System.out.println("Extracting file: " + destinationPath);

                        BufferedInputStream bis = new BufferedInputStream(zipFile.getInputStream(entry));

                        int b;
                        byte buffer[] = new byte[1024];

                        FileOutputStream fos = new FileOutputStream(destinationPath);

                        BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);

                        while ((b = bis.read(buffer, 0, 1024)) != -1) {
                            bos.write(buffer, 0, b);
                        }

                        bos.close();
                        bis.close();

                    }

                }
                return true;
            } catch (IOException ioe) {
                System.out.println("Error opening zip file" + ioe);
            } finally {
                try {
                    if (zipFile != null) {
                        zipFile.close();
                    }
                } catch (IOException ioe) {
                    System.out.println("Error while closing zip file" + ioe);
                }
            }

        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return false;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean mkdir(String directoryPath)">
    /**
     * Crea directorio especificado en el path
     *
     * @param directoryPath
     * @return
     */
    public static Boolean mkdir(String directoryPath) {
        try {
            File directorio = new File(directoryPath);
            if (!directorio.exists()) {
                //Crear el directorio
                if (directorio.mkdirs()) {
                    return true;
//
                } else {
                    return false;
                }
            }

        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String nameOfFileInPath(String filenamePath)">
    /**
     *
     * @param filenamePath
     * @return el nombre del archivo que esta en un path
     */
    public static String nameOfFileInPath(String filenamePath) {
        String name = "";
        try {
            name = filenamePath.substring(filenamePath.lastIndexOf(System.getProperty("file.separator")) + 1,
                    filenamePath.lastIndexOf('.'));
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return name;
    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="isWindows()">
    /*
    Implementado desde el ejemplo de Mkyong
    https://mkyong.com/java/how-to-detect-os-in-java-systemgetpropertyosname/
     */
    public static boolean isWindows() {

        return (opertativeSystem.indexOf("win") >= 0);

    }

    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="isMac()">
    public static boolean isMac() {

        return (opertativeSystem.indexOf("mac") >= 0);

    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="boolean isLinux()">
    public static boolean isLinux() {

        return (opertativeSystem.indexOf("nix") >= 0 || opertativeSystem.indexOf("nux") >= 0 || opertativeSystem.indexOf("aix") > 0);

    }
// </editor-fold>
// <editor-fold defaultstate="collapsed" desc="boolean isSolaris()">

    public static boolean isSolaris() {

        return (opertativeSystem.indexOf("sunos") >= 0);

    }
    // </editor-fold>
// <editor-fold defaultstate="collapsed" desc="String userHome()">

    public static String userHome() {
        return System.getProperty("user.home");

    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="pathOfMicroprofileConfig()">

    /**
     * se usa con Microprofile Config para ejempp
     * #-------------------------------------------------------------- #--Path
     * Images #-- si pathBaseLinuxAddUserHome (solo para Linux
     * JsfUtil.isLinux()) #-- si es true se agrega JsfUtil.userHome() al path
     * (/asistencia/imagenes => /home/miuser/asistencia/imagenes) #-- si es
     * false = se usa el path completo de pathLinux (/opt/asistencia/imagenes)
     * pathBaseLinuxAddUserHome=true pathLinux=/asistencia/imagenes/
     * pathWindows=C:\\asistencia\\imagenes\\
     *
     * @param addUserHome
     * @param pahtLinux
     * @param pathWindows
     * @return
     */
    public static String pathOfMicroprofileConfig(Boolean addUserHome, String pahtLinux, String pathWindows) {
        return isLinux() ? (addUserHome ? userHome() + pahtLinux : pahtLinux) : pathWindows;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="String jsonToString(Object obj)">
    public static String jsonToString(Object obj) {
        String content = "";
        try {
            Jsonb jsonb = JsonbBuilder.create();
            content = jsonb.toJson(obj);
        } catch (Exception e) {
            System.out.println("jsonToString() " + e.getLocalizedMessage());
        }

        return content;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean equals(Object object1, Object object2)">
    /**
     * Compara dos objectos, lo hacemos convirtiendoslos a JSOn y luebo
     * comparando esos objetos json
     *
     * @param object1
     * @param object2
     * @return
     */
    public static Boolean equals(Object object1, Object object2) {
        try {
            Jsonb jsonb = JsonbBuilder.create();
            String json1 = jsonb.toJson(object1);

            String json2 = jsonb.toJson(object2);
            if (json1.equals(json2)) {
                return true;
            }
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return false;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="method()">
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean isPar(Integer number)">
    public static Boolean isPar(Integer number) {
        try {
            if (number % 2 == 0) {
                return Boolean.TRUE;
            }
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" boolean  nameOfJavaIdentifierValid(String identifier)">

    public static boolean nameOfJavaIdentifierValid(String identifier) {
        try {
            String regex = "^([a-zA-Z_$][a-zA-Z\\d_$]*)$";

            Pattern p = Pattern.compile(regex);

            if (identifier == null) {
                return false;
            }

            Matcher m = p.matcher(identifier);

            return m.matches();
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return Boolean.FALSE;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" Boolean isFirstCharacter(String Character)">
    /**
     *
     * @param Character
     * @return Boolean si encuentra el primer caracter coincidente
     */
    public static Boolean isFirstCharacter(String texto, String character) {
        Boolean isValid = Boolean.FALSE;
        try {

            texto = texto.trim();
            int largo = texto.length();
            if (largo <= 0) {
                return Boolean.FALSE;
            }
            String letra = texto.substring(0, 1);
            if (letra.equals(character.trim())) {
                isValid = Boolean.TRUE;
            }

        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return isValid;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" String removeFirstCharacter(String texto)">

    /**
     * Suprime el primer caracter de la cadena
     *
     * @param texto
     * @return
     */
    public static String removeFirstCharacter(String texto) {
        Boolean isValid = Boolean.FALSE;
        try {

            texto = texto.trim();
            int largo = texto.length();
            if (largo <= 0) {
                return "";
            }
            String letra = texto.substring(0, 1);
            texto = texto.replace(letra, "");
            texto = texto.trim();

        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return texto;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Boolean isJmoordbJavaTypeValidQueryRegexCountDelete(String type">
    /**
     * Valida que sea un tipo de datos valido de Java
     *
     * @param type
     * @return
     */
    public static Boolean isJmoordbJavaTypeValid(String type) {
        Boolean isValid = Boolean.FALSE;
        try {

            switch (type) {

                case "java.lang.String":
                case "java.lang.Integer":
                case "java.lang.Float":
                case "java.lang.Character":
                case "java.lang.Long":
                case "java.lang.Double":
                case "java.lang.Boolean":
                case "java.lang.Short":
                case "java.lang.Number":
                case "java.util.Date":
                case "java.time.LocalDateTime":
                case "com.jmoordb.core.model.Pagination":
                case "com.jmoordb.core.model.Sorted":
                case "com.jmoordb.core.model.Search":
                case "com.jmoordb.core.model.Search[]":

                    isValid = Boolean.TRUE;
                    break;
                case "java.util.ArrayList":
                    isValid = Boolean.FALSE;
                default:
                    isValid = Boolean.FALSE;

            }

        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return isValid;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Boolean isJmoordbJavaTypeValidQueryRegexCountDelete(String type">

    /**
     * Valida que sea un tipo de datos valido de Java
     *
     * @param type
     * @return
     */
    public static Boolean isJmoordbJavaTypeValidSaveUpdate(String type) {
        Boolean isValid = Boolean.FALSE;
        try {

            switch (type) {

                case "java.lang.String":
                case "java.lang.Integer":
                case "java.lang.Float":
                case "java.lang.Character":
                case "java.lang.Long":
                case "java.lang.Double":
                case "java.lang.Boolean":
                case "java.lang.Short":
                case "java.lang.Number":
                case "java.util.Date":
                case "com.jmoordb.core.model.Pagination":
                case "com.jmoordb.core.model.Sorted":
                case "com.jmoordb.core.model.Search":
                case "com.jmoordb.core.model.Search[]":

                    isValid = Boolean.TRUE;
                    break;
                case "java.util.ArrayList":
                    isValid = Boolean.FALSE;
                default:
                    isValid = Boolean.FALSE;

            }

        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return isValid;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Integer longToInteger(long number)">
    public static Integer longToInteger(long number) {
        Integer result = 0;
        try {
            result = (int) number;
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return result;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Integer longToInteger(long number)">

    public static Integer longToInteger(Long number) {
        Integer result = 0;
        try {
            result = number.intValue();
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return result;
    }
// </editor-fold>
    // <editor-fold defaultstate="collapsed" desc="Long integerToLong(Integer number)">

    public static Long integerToLong(Integer number) {
        Long result = Long.valueOf(0);
        try {
            result = Long.valueOf(number);
        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " error() " + e.getLocalizedMessage());
        }
        return result;
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="freeSpaceOfDisk() ">
    /**
     *
     * @return espacio libre en disco
     */
    public static Long freeSpaceOfDisk() {
        long freeSpace = 0L;
        try {
            File file = new File("/");
            freeSpace = file.getFreeSpace() / 1024 / 1024;
        } catch (Exception e) {
        }
        return freeSpace;
    }
// </editor-fold>

    public static Long threadCpuTime() {
        String result = "";
        Long usage = 0L;
        try {
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            result += " {[";
            Integer count = 0;
            for (Long threadID : threadMXBean.getAllThreadIds()) {
                ThreadInfo info = threadMXBean.getThreadInfo(threadID);
                if (count != 0) {
                    result += ",";
                }
                result += "{" + "\"Thread name\": \"" + info.getThreadName() + "\" ,\"Thread State\": \"" + info.getThreadState() + "\" ." + String.format("\"CPU time\": %s ns", threadMXBean.getThreadCpuTime(threadID)) + "}";
                count++;
                usage += threadMXBean.getThreadCpuTime(threadID);
            }
            result += "]}";
        } catch (Exception e) {
        }
        return usage;
    }

    public static String memoryUsage() {
        String result = "";
        try {
            MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
            result += String.format("Initial memory: %.2f GB", (double) memoryMXBean.getHeapMemoryUsage().getInit() / 1073741824);
            result += " " + String.format("Used heap memory: %.2f GB", (double) memoryMXBean.getHeapMemoryUsage().getUsed() / 1073741824);
            result += " " + String.format("Max heap memory: %.2f GB", (double) memoryMXBean.getHeapMemoryUsage().getMax() / 1073741824);
            result += " " + String.format("Committed memory: %.2f GB", (double) memoryMXBean.getHeapMemoryUsage().getCommitted() / 1073741824);
        } catch (Exception e) {
        }
        return result;
    }
    
    
    
      // <editor-fold defaultstate="collapsed" desc="String rename_IdToId(String nameOfField)">
    public static String rename_IdToId(String nameOfField) {
        String result = nameOfField;
        try {
            if (nameOfField.toLowerCase().trim().equals("_id")) {
                result = nameOfField.replace("_", "").trim();
            }

        } catch (Exception e) {
            MessagesUtil.error(MessagesUtil.nameOfClassAndMethod() + " " + e.getLocalizedMessage());
        }
        return result;
    }
        // </editor-fold>
    
    
    

}
