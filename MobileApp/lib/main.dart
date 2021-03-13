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
        body: SingleChildScrollView(
          child: SurveyForm()
        ),
      ),
    );
  }
}

class SurveyForm extends StatefulWidget {
  @override
  _SurveyFormState createState() => _SurveyFormState();


}

class _SurveyFormState extends State<SurveyForm> {
  final _surveyFormKey = GlobalKey<FormState>();

  String sendDataLine = "Your data is sent";
  bool dataSent = false;

  GlobalKey<_CustomDropDownState> _genderKey = GlobalKey();
  GlobalKey<_CustomDropDownState> _vaccineKey = GlobalKey();

  bool isVisible = false;

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
      validator: (DateTime value) {
        if (value == null) {
          return "Birth date cannot be empty.";
        } else {
          return null;
        }
      },
      mode: DateTimeFieldPickerMode.date,
      autovalidateMode: AutovalidateMode.always,
      onDateSelected: (DateTime value) {
        //print(value);
      },
    );

    // CITY

    CustomTextFormField cityWidget = new CustomTextFormField(
      hintText: "City",
    );

    // GENDER

    DropDown genderDD = new DropDown(
        defaultValue:'Please select a gender',
        validator: (String value) {
         if (value == 'Please select a gender') {
           return 'Please select a gender';
         } else {
           return null;
         }
        },
        items: ['Please select a gender','Male', 'Female']);

    // VACCINE TYPE

    DropDown vaccineDD = new DropDown(
        defaultValue:'Please select a vaccine',
        validator: (String value) {
          if (value == 'Please select a vaccine') {
            return 'Please select a vaccine';
          } else {
            return null;
          }
        },
        items: ['Please select a vaccine','China-Coronovac', 'Germany-Pfizer', 'USA-Moderna', 'Turkey-Tarhanovac']);

    GlobalKey _dataSentKey = GlobalKey();

    customForms.add(cityWidget);
    customForms.add(nameWidget);
    customForms.add(surnameWidget);

    return Form(
      key: _surveyFormKey,
      onChanged: () {
        final isValid = _surveyFormKey.currentState.validate();
        if (isVisible != isValid) {
          setState(() {
            isVisible = isValid;
          });
        }
      },
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

          Container(
              child: cityWidget,
          ),

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

          CustomTextFormField(
            hintText: "Side effect after vaccination",
          ),

          Visibility(
            visible: isVisible,
            child: MaterialButton(
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
              },
            ),
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
    String _text;
    return Padding(
      padding: EdgeInsets.all(8.0),
      child: TextFormField(
        onSaved: (str) => _text = str,
        autovalidateMode: AutovalidateMode.always,
        validator: this.validator,
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
            autovalidateMode: AutovalidateMode.always,
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