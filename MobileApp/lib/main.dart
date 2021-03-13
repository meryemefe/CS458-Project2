import 'dart:async';

import 'package:flutter/material.dart';
import 'package:date_field/date_field.dart';
import 'package:flutter/rendering.dart';

void main() {
  runApp(SurveyApp());
}

class SurveyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold (
        appBar: AppBar(
          title: new Center(
            child: Text("Covid-19 Survey", textAlign: TextAlign.center)
          ),
          backgroundColor: Colors.lightGreen,
        ),
        body: SurveyForm(),
      ),
    );
  }
}

class SurveyForm extends StatefulWidget {
  @override
  _SurveyFormState createState() => _SurveyFormState();
}

class DropDown{
  String defaultValue;
  List<String> items;
  final Function validator;
  DropDown({this.defaultValue, this.validator, this.items});
}

class CustomDropDown extends StatefulWidget{
  DropDown content;
  Key key;
  CustomDropDown({this.key, this.content});
  @override
  _CustomDropDownState createState() => _CustomDropDownState(content);
}



class _SurveyFormState extends State<SurveyForm> {
  final _surveyFormKey = GlobalKey<FormState>();
  bool dataSent = false;
  String sendDataLine = "Your data is sent";

  GlobalKey<_CustomDropDownState> _genderKey = GlobalKey();
  GlobalKey<_CustomDropDownState> _vaccineKey = GlobalKey();



  void resetForm(List<CustomTextFormField> customForms){
    _surveyFormKey.currentState.reset();
    _genderKey.currentState.reset();
    _vaccineKey.currentState.reset();
    for(CustomTextFormField field in customForms){
      field.reset();
    }
  }

  @override
  Widget build(BuildContext context) {
    final halfScreenWidth = MediaQuery.of(context).size.width / 2.0;


    DropDown genderDD = new DropDown(
        defaultValue:'Please select a gender',
        validator: (String value) =>  (value == 'Please select a gender') ? 'Please select a gender' : null,
        items: ['Please select a gender','Male', 'Female']);
    DropDown vaccineDD = new DropDown(
        defaultValue:'Please select a vaccine',
        validator: (String value) =>  (value == 'Please select a vaccine') ? 'Please select a vaccine' : null,
        items: ['Please select a vaccine','China-Coronovac', 'Germany-Pfizer', 'USA-Moderna', 'Turkey-Tarhanovac']);

    List<CustomTextFormField> customForms = [];

    // NAME

    CustomTextFormField nameWidget = new CustomTextFormField(
            hintText: "Name",
            validator: (String value) {
              if (value.isEmpty) {
                return 'Name cannot be empty.';
              } else if (value.length == 1) {
                return "Name cannot be 1\ncharacter.";
              } else if (RegExp(r'[!@#<>?":_`~;[\]\\|=+)(*&^%0-9-]').hasMatch(value)) {
                return "Name cannot contain\nnumerical character.";
              } else {
                return null;
              }
            },
        );

    // SURNAME

    CustomTextFormField surnameWidget  = new CustomTextFormField(
      hintText: "Surname",
      validator: (String value) {
        if (value.isEmpty) {
          return 'Surname cannot be empty.';
        } else if (value.length == 1) {
          return "Surname cannot be 1\ncharacter.";
        } else if (RegExp(r'[!@#<>?":_`~;[\]\\|=+)(*&^%0-9-]').hasMatch(value)) {
          return "Surname cannot contain\nnumerical character.";
        } else {
          return null;
        }
      },
    );

    // DATE

    FormField dateWidget = new DateTimeFormField(
      decoration: const InputDecoration(
        hintStyle: TextStyle(color: Colors.black45),
        errorStyle: TextStyle(color: Colors.redAccent),
        border: OutlineInputBorder(
            borderRadius: BorderRadius.all(Radius.circular(12.0))
        ),
        suffixIcon: Icon(Icons.event_note),
        labelText: "Birth Date",
        contentPadding: EdgeInsets.all(12),
        filled: true,
        fillColor: Colors.white,
      ),
      mode: DateTimeFieldPickerMode.date,
      autovalidateMode: AutovalidateMode.always,
      validator: (DateTime value) {
        if (value == null) {
          return null;
        } else if (value.isAfter(DateTime.now())) {
          return "Date cannot be future date";
        } else if (value.isBefore(new DateTime(2021))) {
          return "Date cannot be before 2021";
        } else {
          return null;
        }
      },
      onDateSelected: (DateTime value) {
        //print(value);
      },
    );

    // CITY

    CustomTextFormField cityWidget = new CustomTextFormField( // BURAYA CITY LİSTE ŞEKLİNDE KONULABİLİR VEYA TEXT
      hintText: "City",
      validator: (String value) {
        if (value.isEmpty) {
          return 'City cannot be empty.';
        } else {
          return null;
        }
      },
    );

    GlobalKey _dataSentKey = GlobalKey();

    customForms.add(cityWidget);
    customForms.add(nameWidget);
    customForms.add(surnameWidget);


    return Form(
      key: _surveyFormKey,
      child: Column(
        children: <Widget>[
          Row (
            crossAxisAlignment: CrossAxisAlignment.start,
            children: <Widget>[
              Container(
                alignment: Alignment.topLeft,
                width: halfScreenWidth,
                child: nameWidget,
              ),
              Container(
                alignment: Alignment.topRight,
                width: halfScreenWidth,
                child: surnameWidget,
              ),
            ],
          ),
          Padding(
            padding: EdgeInsets.all(8.0),
            child: Container(
              child: dateWidget,
            ),
          ),

          cityWidget,
          CustomDropDown(
              key: _genderKey,
              content: genderDD),
          CustomDropDown(
              key: _vaccineKey,
              content: vaccineDD),
//          CustomTextFormField(
//            hintText: "Gender",
//            validator: null,
//          ),
//          CustomTextFormField(
//            hintText: "Vaccine type they applied",
//          ),

          CustomTextFormField( // TEXT BOX BÜYÜTÜLEBİLİR
            hintText: "Side effect after vaccination",
          ),

          MaterialButton( // BUTONA BASINCA SAYFA DEĞİŞİP BAŞARI ŞEKİLDE GÖNDERİLDİ YAZDIRILABİLİR
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(17.0),
            ),
            color: Colors.blueAccent,
            textColor: Colors.white,
            child: Text(
              "Send",
              style: TextStyle(
                color: Colors.white,
              ),
            ),
            onPressed: () async {

              if (_surveyFormKey.currentState.validate()) {
                setState(() {
                  dataSent = true;
                  if (_vaccineKey.currentState.chosenValue == 'Germany-Pfizer'){
                    sendDataLine = "Ihre Daten werden gesendet!";
                  }else if (_vaccineKey.currentState.chosenValue == 'China-Coronovac'){
                    sendDataLine = "Nín de shùjù yǐ fāsòng!";
                  }else if (_vaccineKey.currentState.chosenValue == 'Turkey-Tarhanovac'){
                    sendDataLine = "Bilgileriniz gönderildi!";
                  } else {
                    sendDataLine = "Your data is sent!";
                  }
                  resetForm(customForms);
                });
              }
            },
          ),
          Offstage (
            key: _dataSentKey,
            offstage: !dataSent,
            child: Container(
                padding: EdgeInsets.all(8.0),
                child: Text(sendDataLine,
                  style: TextStyle(fontSize: 25, fontWeight: FontWeight.bold),) ),
          ),
        ],
      )
    );
  }
}

class CustomTextFormField extends StatelessWidget {
  final String hintText;
  final Function validator;
  var content = TextEditingController();
  Key key;
  CustomTextFormField({
    this.key,
    this.hintText,
    this.validator
});
  void reset(){
    content.text = "";
  }
  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.all(8.0),
      child: TextFormField(
        validator: this.validator,
        controller: content,
        decoration: InputDecoration(
          hintText: hintText,
          contentPadding: EdgeInsets.all(12),
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(15.0),
          ),
          filled: true,
          fillColor: Colors.white,
        ),
      ),
    );
  }
}

class _CustomDropDownState extends State<CustomDropDown> {
  String defaultValue;
  String chosenValue;
  Function validator;
  List<String> items;

  _CustomDropDownState(DropDown _content){
    defaultValue = _content.defaultValue;
    chosenValue = _content.defaultValue;
    validator = _content.validator;
    items = _content.items;
  }

  @override
  Widget build(BuildContext context){
    return Padding(
        padding: EdgeInsets.all(8.0),
        child: Container(
          alignment: Alignment.center,
          decoration: BoxDecoration(
            borderRadius: BorderRadius.circular(12.0),
            color: Colors.white,
          ),
          child: DropdownButtonFormField<String>(
            isExpanded: true,
            focusColor:Colors.white,
            value: chosenValue,
            style: TextStyle(color: Colors.white),
            iconEnabledColor:Colors.black,
            decoration: InputDecoration(
              contentPadding: EdgeInsets.all(12),
              border: OutlineInputBorder(
                borderRadius: BorderRadius.circular(15.0),
              ),
              filled: true,
              fillColor: Colors.white,
            ),
            items: items.map<DropdownMenuItem<String>>((String value) {
              return DropdownMenuItem<String>(
                value: value,
                child: Text(
                  value,
                  style:TextStyle(color:Colors.black),),
              );
            }).toList(),
            validator: validator,
            onChanged: (String value) {
              setState(() {
                chosenValue = value;
              });
            },
          ),
        ),
    );
  }

  void printValue(){
    print(chosenValue);
  }

  void reset(){
    setState(() {
      chosenValue = defaultValue;
    });
  }

}